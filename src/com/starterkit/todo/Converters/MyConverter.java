package com.starterkit.todo.Converters;

import org.eclipse.core.databinding.conversion.IConverter;

import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.DataModel.Status;

public class MyConverter {


	public static IConverter createIntToStatus() {
	    return new IConverter() {

	        @Override
	        public Object convert(Object value) {
	        int val=(int)value;
	            return Status.values()[val];
	
	        }

	        @Override
	        public Object getFromType() {
	            return Integer.class;
	        }

	        @Override
	        public Object getToType() {
	            return Status.class;
	        }

	    };
	}

	public static IConverter createStatusToIntConverter() {
	    return new IConverter() {

	        @Override
	        public Object convert(Object value) {
	        	Status p=(Status) value;
	            return p.ordinal();
	        }

	        @Override
	        public Object getFromType() {
	            return Status.class;
	        }

	        @Override
	        public Object getToType() {
	            return Integer.class;
	        }

	    }
	    ;
	}
	public static IConverter createIntToPriority() {
	    return new IConverter() {

	        @Override
	        public Object convert(Object value) {
	        int val=(int)value;
	            return Priority.values()[val];
	
	        }

	        @Override
	        public Object getFromType() {
	            return Integer.class;
	        }

	        @Override
	        public Object getToType() {
	            return Priority.class;
	        }

	    };
	}

	public static IConverter createPriorityToIntConverter() {
	    return new IConverter() {

	        @Override
	        public Object convert(Object value) {
	        Priority p=(Priority) value;
	            return p.ordinal();
	        }

	        @Override
	        public Object getFromType() {
	            return Priority.class;
	        }

	        @Override
	        public Object getToType() {
	            return Integer.class;
	        }

	    };
}
}
