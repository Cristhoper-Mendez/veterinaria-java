package veterinaria.mm22116.interfaces;

import veterinaria.mm22116.entidades.Cita;
import java.util.List;

public interface ICitaServicio {

    public List<Cita> ObtenerCitas();

    public List<Cita> ObtenerCitasActivas();

    public boolean CrearCita(Cita cita);

    public boolean ActualizarCita(Cita cita);

    public boolean EliminarCita(int idCita);

    public List<Cita> ObtenerCitasPorPacienteId(int idPaciente);
}
