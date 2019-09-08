package com.hbe.ms.common.exception;


public class HBEMSPersistenceException extends RuntimeException {

   private static final long serialVersionUID = 1409075783578543469L;

   public HBEMSPersistenceException() {
      super();
   }

   public HBEMSPersistenceException(String message, Exception e) {
      super(message, e);
   }

   public HBEMSPersistenceException(String message) {
      super(message);
   }

   public HBEMSPersistenceException(Throwable e) {
      super(e);
   }

}
