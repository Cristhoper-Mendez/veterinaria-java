package veterinaria.mm22116.interfaces;

import veterinaria.mm22116.entidades.Vacunas;
import java.util.List;

public interface IVacunasServicio {

    public List<Vacunas> ObtenerVacunas();

    public List<Vacunas> ObtenerVacunasActivas();

    public boolean CrearVacunas(Vacunas vacuna);

    public boolean ActualizarVacunas(Vacunas vacuna);

    public boolean EliminarVacunas(int vacunaId);

    public List<Vacunas> ObtenerVacunasPorIdPaciente(int idPaciente);
}
