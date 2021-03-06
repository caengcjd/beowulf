/*
 * GNU LESSER GENERAL PUBLIC LICENSE Copyright (C) 2006 The Lobo Project
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 * 
 * Contact info: lobochief@users.sourceforge.net
 */
package com.nvarghese.beowulf.common.cobra.js;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.WrappedException;

import com.nvarghese.beowulf.common.cobra.util.Objects;

public class JavaFunctionObject extends ScriptableObject implements Function {

	private static final Logger logger = Logger.getLogger(JavaFunctionObject.class.getName());
	private static final boolean loggableInfo = logger.isLoggable(Level.INFO);
	private final String className;
	private final transient ArrayList methods = new ArrayList();

	public JavaFunctionObject(final String name) {

		super();
		this.className = name;
	}

	public void addMethod(final Method m) {

		this.methods.add(m);
	}

	public String getClassName() {

		return this.className;
	}

	private String getTypeName(final Object object) {

		return object == null ? "[null]" : object.getClass().getName();
	}

	private Method getBestMethod(final Object[] args) {

		ArrayList methods = this.methods;
		int size = methods.size();
		int matchingNumParams = 0;
		Method matchingMethod = null;
		for (int i = 0; i < size; i++) {
			Method m = (Method) methods.get(i);
			Class[] parameterTypes = m.getParameterTypes();
			if (args == null) {
				if (parameterTypes == null || parameterTypes.length == 0) {
					return m;
				}
			} else if (parameterTypes != null && args.length >= parameterTypes.length) {
				if (Objects.areAssignableTo(args, parameterTypes)) {
					return m;
				}
				if (matchingMethod == null || parameterTypes.length > matchingNumParams) {
					matchingNumParams = parameterTypes.length;
					matchingMethod = m;
				}
			}
		}
		if (size == 0) {
			throw new IllegalStateException("zero methods");
		}
		return matchingMethod;
	}

	public Object call(final Context cx, final Scriptable scope, final Scriptable thisObj, final Object[] args) {

		JavaObjectWrapper jcw = (JavaObjectWrapper) thisObj;
		Method method = this.getBestMethod(args);
		if (method == null) {
			throw new EvaluatorException("No method matching " + this.className + " with " + (args == null ? 0 : args.length) + " arguments.");
		}
		Class[] actualArgTypes = method.getParameterTypes();
		int numParams = actualArgTypes.length;
		Object[] actualArgs = args == null ? new Object[0] : new Object[numParams];
		boolean linfo = loggableInfo;
		if (linfo) {
			Object javaObject = jcw.getJavaObject();
			logger.info("call(): Calling method " + method.getName() + " on object " + javaObject + " of type " + this.getTypeName(javaObject));
		}
		JavaScript manager = JavaScript.getInstance();
		for (int i = 0; i < numParams; i++) {
			Object arg = args[i];
			Object actualArg = manager.getJavaObject(arg, actualArgTypes[i]);
			if (linfo) {
				logger.info("call(): For method=" + method.getName() + ": Converted arg=" + arg + " (type=" + this.getTypeName(arg)
						+ ") into actualArg=" + actualArg + ". Type expected by method is " + actualArgTypes[i].getName() + ".");
			}
			actualArgs[i] = actualArg;
		}
		try {
			Object raw = method.invoke(jcw.getJavaObject(), actualArgs);
			return manager.getJavascriptObject(raw, scope);
		} catch (IllegalAccessException iae) {
			throw new IllegalStateException("Unable to call " + this.className + ".", iae);
		} catch (InvocationTargetException ite) {
			throw new WrappedException(new InvocationTargetException(ite.getCause(), "Unable to call " + this.className + " on "
					+ jcw.getJavaObject() + "."));
		} catch (IllegalArgumentException iae) {
			StringBuffer argTypes = new StringBuffer();
			for (int i = 0; i < actualArgs.length; i++) {
				if (i > 0) {
					argTypes.append(", ");
				}
				argTypes.append(actualArgs[i] == null ? "<null>" : actualArgs[i].getClass().getName());
			}
			throw new WrappedException(new IllegalArgumentException("Unable to call " + this.className + ". Argument types: " + argTypes + ".", iae));
		}
	}

	public java.lang.Object getDefaultValue(final java.lang.Class hint) {

		if (loggableInfo) {
			logger.info("getDefaultValue(): hint=" + hint + ",this=" + this);
		}
		if (hint == null || String.class.equals(hint)) {
			return "function " + this.className;
		} else {
			return super.getDefaultValue(hint);
		}
	}

	public Scriptable construct(final Context cx, final Scriptable scope, final Object[] args) {

		throw new UnsupportedOperationException();
	}
}
