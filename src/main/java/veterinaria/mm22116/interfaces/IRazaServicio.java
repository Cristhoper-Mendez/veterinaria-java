package veterinaria.mm22116.interfaces;

import veterinaria.mm22116.entidades.Raza;
import java.util.List;

public interface IRazaServicio {
//    public String

    public List<Raza> ObtenerRazasActivas();

    public List<Raza> ObtenerRazas();

    public boolean CrearRaza(Raza raza);

    public boolean ActualizarRaza(Raza raza);

    public boolean EliminarRaza(int razaId);

    public Raza ObtenerRazaPorId(int idRaza);

    public List<Raza> ObtenerRazasPorTipoPaciente(int idTipoPaciente);
}
