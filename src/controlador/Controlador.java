package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;
import modelo.EstudianteDAO;
import vista.Vista;

public class Controlador implements ActionListener {

    EstudianteDAO estudianteDAO = new EstudianteDAO();
    Estudiante estudiante = new Estudiante();
    Vista vista = new Vista();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    

    public Controlador(Vista vista){
        this.vista = vista;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Telefono");
        actualizarListaDatos();
        
    }
    
    
    public void actualizarListaDatos(){
        for (Estudiante estudiante : estudianteDAO.listar()) {  
            //Object[] datosFila = {estudiante.getId(), estudiante.getNombres(), estudiante.getCorreo(), estudiante.getTelefono()};
            modeloTabla.addRow(new Object[] {estudiante.getId(), estudiante.getNombres(), estudiante.getCorreo(), estudiante.getTelefono()});
        }
        vista.tblTabla.setModel(modeloTabla);
    }
    
    public void limpiarCampos() {
        vista.txtID.setText("");
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtTelefono.setText("");
        vista.txtNombre.requestFocus();
    }

    public void limpiarTabla() {
        for (int i = 0; i < vista.tblTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
        }
    }
    
    public void eliminarDatoSeleccionado(){
        int fila = vista.tblTabla.getSelectedRow();
        if(fila ==  -1){
            JOptionPane.showMessageDialog(vista, "Error, seleccione un elemento de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            int idAEliminar = Integer.parseInt((String) vista.tblTabla.getValueAt(fila, 0).toString()); // obtengo el valor del id que se esta seleccionando en este momento
            
            //int  filasAfectadas = estudianteDAO.eliminar(idAEliminar); // elimino de la base de datos
            if(estudianteDAO.eliminar(idAEliminar) == 1){
                JOptionPane.showMessageDialog(vista, "Usuario eliminado con exito.", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al eliminar", "Eliminar", JOptionPane.ERROR_MESSAGE);
            }
            limpiarTabla();
            
            
        }
    }
    
    public void agregarEstudiante(){
        String nombre = vista.txtNombre.getText();
        String correo = vista.txtCorreo.getText();
        String telefono = vista.txtTelefono.getText();
        
        estudiante.setNombres(nombre);
        estudiante.setCorreo(correo);
        estudiante.setTelefono(telefono);
        
        int filasAfectada = estudianteDAO.agregar(estudiante); // si afecta filas significa que el registro fue agregado correctamente
        if (filasAfectada == 1) {
            JOptionPane.showMessageDialog(vista, "Estudiante agregado correctamente.", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(vista, "Error al agregar estudiante.", "Agregar", JOptionPane.ERROR_MESSAGE);
        }
        limpiarTabla();
        limpiarCampos();
    }
    
    public void actualizarDatos(){
        if(vista.txtID.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(vista, "Error, no hay nada seleccionado", "Actulizar", 0);
        }else{
            int id = Integer.parseInt(vista.txtID.getText());
            String nombre = vista.txtNombre.getText();
            String correo = vista.txtCorreo.getText();
            String telefono = vista.txtTelefono.getText();
            estudiante.setId(id);
            estudiante.setNombres(nombre);
            estudiante.setCorreo(correo);
            estudiante.setTelefono(telefono);
            
            int filasAfectada = estudianteDAO.actualizar(estudiante);
            if(filasAfectada == 1){
                JOptionPane.showMessageDialog(vista, "Estudiante actualizado correctamente.", "Actualizar", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al actualizar", "Actualizar", JOptionPane.ERROR_MESSAGE);
            }
        }
        limpiarCampos();
        limpiarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnListar){
            limpiarTabla();
            actualizarListaDatos();
            limpiarCampos();
        }
        if(e.getSource() == vista.btnAgregar){
            if (vista.txtNombre.getText().equals("") || vista.txtCorreo.getText().equals("") || 
                    vista.txtTelefono.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Error, un campo esta sin completar. Por favor completelo.",
                        "Error", 0);           
            }else{
                agregarEstudiante();
                //limpiarTabla();
                actualizarListaDatos();
                limpiarCampos();
            }
        }
        if(e.getSource() == vista.btnEditar){
            int fila = vista.tblTabla.getSelectedRow();
            if (fila == -1){
                JOptionPane.showMessageDialog(vista, "Error, seleccione un elemento de la tala", "Error", 0);
            }else{
                // recojo los datos de la tabla para agregarlos a los campos posteriormente y realizar la edicion a partir de esos campos
                int id = Integer.parseInt((String) vista.tblTabla.getValueAt(fila,0).toString());
                String nombre = (String) vista.tblTabla.getValueAt(fila,1);
                String correo = (String) vista.tblTabla.getValueAt(fila,2);
                String telefono = (String) vista.tblTabla.getValueAt(fila,3);
                
                vista.txtID.setText(id+"");
                vista.txtNombre.setText(nombre);
                vista.txtCorreo.setText(correo);
                vista.txtTelefono.setText(telefono);
                JOptionPane.showMessageDialog(vista, "Ahora edite los datos y presione actualizar", "Editar", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getSource() == vista.btnActualizar){
            actualizarDatos();
            limpiarCampos();
            limpiarTabla();
            actualizarListaDatos();
        }
        if(e.getSource() == vista.btnEliminar){
            System.out.println("presionado");
            eliminarDatoSeleccionado();
            limpiarCampos();
            limpiarTabla();
            actualizarListaDatos();
        }
    }

}
