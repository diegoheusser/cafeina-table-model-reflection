//TableModel.java
package view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Getter;
import model.Setter;

/**
 * @author Diego Heusser
 */
public class TableModel extends AbstractTableModel {

    private List<Object> list;

    //Builder
    public TableModel(List<Object> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        if (list.isEmpty()) {
            return 0;
        }
        Object object = list.get(0);
        Class<?> c = object.getClass();

        int column = 0;
        for (Method method : c.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Getter.class)) {
                column++;
            }
        }
        return column;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Object object = list.get(rowIndex);
            Class<?> c = object.getClass();
            for (Method method : c.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Getter.class)) {
                    Getter getter = method.getAnnotation(Getter.class);
                    if (getter.column() == columnIndex) {
                        return method.invoke(object);
                    }
                }
            }
        } catch (IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException |
                NullPointerException ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (!list.isEmpty()) {
            if (list.size() > rowIndex) {
                Class<?> c = list.get(rowIndex).getClass();
                for (Method method : c.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Setter.class)) {
                        Setter setter = method.getAnnotation(Setter.class);
                        if (setter.column() == columnIndex) {
                            try {
                                method.invoke(list.get(rowIndex), aValue);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (list.isEmpty()) {
            return false;
        }
        Object object = list.get(rowIndex);
        Class<?> c = object.getClass();
        for (Method method : c.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Getter.class)) {
                Getter getter = method.getAnnotation(Getter.class);
                if (getter.column() == columnIndex) {
                    return getter.editable();
                }
            }
        }
        return false;
    }

    @Override
    public String getColumnName(int column) {
        if (list.isEmpty()) {
            return null;
        }
        Object object = list.get(0);
        Class<?> c = object.getClass();
        for (Method method : c.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Getter.class)) {
                Getter getter = method.getAnnotation(Getter.class);
                if (getter.column() == column) {
                    return getter.name();
                }
            }
        }
        return null;
    }

}
