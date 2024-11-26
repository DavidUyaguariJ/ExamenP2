import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class ListaHeroes {

    public  Nodo inicio ;
    public int tamano ;

    public ListaHeroes() {
        this.inicio=null;
        this.tamano=0;

    }
    public void contarPorUniverso(Nodo nodo, List<UniversoConteo> conteos) {
        if (nodo == null) {
            return;
        }

        String universoActual = nodo.hero.getUniverso();
        boolean encontrado = false;

        for (UniversoConteo conteo : conteos) {
            if (conteo.getUniverso().equals(universoActual)) {
                conteo.setConteo(conteo.getConteo() + 1);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            conteos.add(new UniversoConteo(universoActual, 1));
        }
        contarPorUniverso(nodo.siguiente, conteos);
    }

    public void agregar(SpiderverseHero hero, DefaultTableModel tableModel){
        Nodo nuevoNodo= new Nodo(hero);
        if (inicio== null){
            inicio = nuevoNodo;
        }else{
            Nodo actual = inicio ;
            while (actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
        actualizarLista(tableModel);
    }

    public SpiderverseHero buscarLineal(int codigo,DefaultTableModel tableModel){
            ordenarBurbuja(tableModel);
            Nodo actual = inicio;
            while (actual!=null) {
                if (actual.hero.getCodigo() == codigo) {
                    return actual.hero;
                }
                actual = actual.siguiente;
            }
            return null;
    }

    public SpiderverseHero buscarLinealPorPoder(String poder,DefaultTableModel tableModel){
        Nodo actual = inicio;
        while (actual!=null) {
            if (actual.hero.getPoderesEspeciales() != poder) {
                tableModel.addRow(new Object[]{actual.hero.getCodigo(),actual.hero.getNombre(),actual.hero.getNivelExperiencia(),actual.hero.getUniverso(),actual.hero.getPoderesEspeciales()});
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public void ordenarBurbujaporExperiencia(DefaultTableModel tableModel){
        if(inicio==null||inicio.siguiente==null)
            return;
        boolean intermediario;
        do{
            intermediario= false;
            Nodo actual = inicio;
            Nodo siguiente = inicio.siguiente;
            while(siguiente!=null){
                if (actual.hero.getNivelExperiencia()>siguiente.hero.getNivelExperiencia()){
                    int temporal = actual.hero.getNivelExperiencia();
                    actual.hero.setNivelExperiencia(siguiente.hero.getNivelExperiencia());
                    siguiente.hero.setNivelExperiencia(temporal);
                    intermediario=true;

                }
                actual=siguiente;
                siguiente=siguiente.siguiente;
            }
        }while(intermediario);
        actualizarLista(tableModel);
    }

    public void ordenarBurbuja(DefaultTableModel tableModel){
        if(inicio==null||inicio.siguiente==null)
            return;
        boolean intermediario;
        do{
            intermediario= false;
            Nodo actual = inicio;
            Nodo siguiente = inicio.siguiente;
            while(siguiente!=null){
                if (actual.hero.getCodigo()>siguiente.hero.getCodigo()){
                    int temporal = actual.hero.getCodigo();
                    actual.hero.setCodigo(siguiente.hero.getCodigo());
                    siguiente.hero.setCodigo(temporal);
                    intermediario=true;

                }
                actual=siguiente;
                siguiente=siguiente.siguiente;
            }
        }while(intermediario);
        actualizarLista(tableModel);
    }

    public void actualizarLista(DefaultTableModel tableModel){
        if (inicio==null){
            JOptionPane.showMessageDialog(null, "Tabla Vacía");
        }else{
            tableModel.setRowCount(0);
            tableModel.addRow(new Object[]{"Codigo", "Nombre", "Experiencia", "Universo", "Poderes"});
            Nodo actual = inicio;
            while (actual!=null){
                SpiderverseHero hero = actual.hero;
                actual=actual.siguiente;
                tableModel.addRow(new Object[]{hero.getCodigo(),hero.getNombre(),hero.getNivelExperiencia(),hero.getUniverso(),hero.getPoderesEspeciales()});

            }
        }
    }



}