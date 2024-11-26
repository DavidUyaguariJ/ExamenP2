public class SpiderverseHero {
    private int codigo;
    private String nombre;
    private String poderesEspeciales;
    private String universo;
    private int nivelExperiencia;

    public SpiderverseHero(int codigo, String nombre, String poderesEspeciales, String universo, int nivelExperiencia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poderesEspeciales = poderesEspeciales;
        this.universo = universo;
        this.nivelExperiencia = nivelExperiencia;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPoderesEspeciales() {
        return poderesEspeciales;
    }

    public String getUniverso() {
        return universo;
    }

    public int getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPoderesEspeciales(String poderesEspeciales) {
        this.poderesEspeciales = poderesEspeciales;
    }

    public void setUniverso(String universo) {
        this.universo = universo;
    }

    public void setNivelExperiencia(int nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }


}
