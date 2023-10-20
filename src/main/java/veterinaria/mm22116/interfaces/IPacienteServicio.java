package veterinaria.mm22116.interfaces;

import veterinaria.mm22116.entidades.Paciente;
import java.util.List;

public interface IPacienteServicio {

    public List<Paciente> ObtenerPacientes();

    public List<Paciente> ObtenerPacientesActivos();

    public boolean CrearPaciente(Paciente paciente);

    public boolean ActualizarPaciente(Paciente paciente);

    public boolean EliminarPaciente(int pacienteId);

    public Paciente ObtenerPacientePorId(int idPaciente);

}
