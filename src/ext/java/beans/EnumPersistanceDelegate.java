/*
 * EnumPersistanceDelegate.java
 *
 * Created on January 26, 2007, 11:41 AM
 *
 */

package ext.java.beans;

/**
 *
 * @author matt@slc
 * To install for an enum type add:
 * <code/>static {EnumPersistanceDelegate.installFor(values());}</code>
 */
import java.util.*;
import java.beans.*;

public class EnumPersistanceDelegate extends DefaultPersistenceDelegate {
  private static EnumPersistanceDelegate INSTANCE = new EnumPersistanceDelegate();
  public static void installFor(Enum<?>[] values) {
    Class<? extends Enum> declaringClass = values[0].getDeclaringClass();
    installFor(declaringClass);
    for (Enum<?> e : values)
      if (e.getClass() != declaringClass)
        installFor(e.getClass());
  }
  static void installFor(Class<? extends Enum> enumClass) {
    try {
      BeanInfo info = Introspector.getBeanInfo( enumClass );
      info.getBeanDescriptor().setValue( "persistenceDelegate", INSTANCE );
    }
    catch( IntrospectionException exception ) {
      throw new RuntimeException("Unable to persist enumerated type "+enumClass, exception);
    }
  }
  protected Expression instantiate(Object oldInstance, Encoder out) {
    Enum e = (Enum)oldInstance;
    return new Expression(Enum.class, "valueOf",
      new Object[] { e.getDeclaringClass(), e.name() });
  }
  protected boolean mutatesTo(Object oldInstance, Object newInstance) {
    return oldInstance == newInstance;
  }
}
