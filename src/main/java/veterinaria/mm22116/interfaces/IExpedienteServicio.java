package veterinaria.mm22116.interfaces;

import veterinaria.mm22116.entidades.Expediente;
import java.util.List;

public interface IExpedienteServicio {

    public List<Expediente> ObtenerExpediente();

    public List<Expediente> ObtenerExpedienteActivas();

    public boolean CrearExpediente(Expediente expediente);

    public boolean ActualizarExpediente(Expediente expediente);

    public boolean EliminarExpediente(int expedienteId);
    
    public List<Expediente> ObtenerExpedientePorIdPaciente(int idPaciente);

}
