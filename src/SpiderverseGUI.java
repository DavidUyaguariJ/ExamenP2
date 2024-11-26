import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SpiderverseGUI {
    private JPanel pGeneral;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox comboExperiencia;
    private JComboBox comboUniversos;
    private JComboBox comboPoderes;
    private JButton btnPasar;
    private JTable tbtHeroes;
    private JTextField txtCodigoBusqueda;
    private JButton btnBuscar;
    private JComboBox comboPoderEx;
    private JTable newTable;
    private JButton ordenarButton;
    private JButton buscarPorUniversoButton;
    private JTextArea txtUniversos;
    private JButton btnAgregar;
    private JButton limpiar;
    private DefaultTableModel tableModel;
    private DefaultTableModel tableModel2;
    private ListaHeroes listaHeroes = new ListaHeroes();
    public SpiderverseGUI() {
        tableModel = new DefaultTableModel();
        tableModel2 = new DefaultTableModel();
        tableModel.addColumn("Codigo");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Experiencia");
        tableModel.addColumn("Universo");
        tableModel.addColumn("Poderes");
        tbtHeroes.setModel(tableModel);

        tableModel2.addColumn("Codigo");
        tableModel2.addColumn("Nombre");
        tableModel2.addColumn("Experiencia");
        tableModel2.addColumn("Universo");
        tableModel2.addColumn("Poderes");
        newTable.setModel(tableModel2);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo=0;
                try{
                    codigo= Integer.parseInt(txtCodigo.getText());
                }
                catch(Exception ex){
                    JOptionPane.showInternalMessageDialog(null, ex.getMessage());
                }
                if(listaHeroes.buscarLineal(codigo, tableModel)!=null){
                    JOptionPane.showMessageDialog(null, "El Heroe con ese id ya existe: ");
                }else{
                    String experiencia = (String) comboExperiencia.getSelectedItem();
                    String nombre= txtNombre.getText();
                    String universo = (String) comboUniversos.getSelectedItem();
                    String poderes = (String) comboPoderes.getSelectedItem();
                    int experienciaEntero= Integer.parseInt(experiencia);
                    SpiderverseHero hero= new SpiderverseHero(codigo,nombre,poderes,universo,experienciaEntero);
                    listaHeroes.agregar(hero,tableModel);
                    txtNombre.setText("");
                    txtCodigo.setText("");
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int busqueda;
                try{
                    busqueda= Integer.parseInt(txtCodigoBusqueda.getText());
                    SpiderverseHero heroeBuscar = listaHeroes.buscarLineal(busqueda, tableModel);
                    if(heroeBuscar==null){
                        JOptionPane.showMessageDialog(null, "El Heroe no existe");
                    }else{
                        String codigo= Integer.toString(heroeBuscar.getCodigo());
                        String nivelEx=Integer.toString(heroeBuscar.getNivelExperiencia());
                        txtCodigo.setText(codigo);
                        txtNombre.setText(heroeBuscar.getNombre());
                        comboExperiencia.setSelectedItem(nivelEx);
                        comboUniversos.setSelectedItem(heroeBuscar.getUniverso());
                        comboPoderes.setSelectedItem(heroeBuscar.getPoderesEspeciales());
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        btnPasar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String poder= (String)comboPoderEx.getSelectedItem();
                listaHeroes.buscarLinealPorPoder(poder, tableModel2);
            }
        });
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaHeroes.ordenarBurbujaporExperiencia(tableModel2);
            }
        });
        buscarPorUniversoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaHeroes.inicio == null) {
                    JOptionPane.showMessageDialog(null, "No hay héroes registrados para realizar el conteo.");
                    return;
                }

                List<UniversoConteo> conteos = new ArrayList<>();

                listaHeroes.contarPorUniverso(listaHeroes.inicio, conteos);

                StringBuilder resultado = new StringBuilder("Conteo de Héroes por Universo:\n");
                for (UniversoConteo conteo : conteos) {
                    resultado.append("Universo ").append(conteo.getUniverso())
                            .append(": ").append(conteo.getConteo()).append(" héroes\n");
                }

                txtUniversos.setText(resultado.toString());
            }

        });
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUniversos.setText("");
                tableModel.setRowCount(0);
                tableModel2.setRowCount(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SpiderverseGUI");
        frame.setContentPane(new SpiderverseGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
        frame.setVisible(true);
    }
}
