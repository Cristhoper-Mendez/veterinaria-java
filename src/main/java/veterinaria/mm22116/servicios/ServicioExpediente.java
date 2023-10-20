package veterinaria.mm22116.servicios;

import java.util.List;
import java.util.ArrayList;
import veterinaria.mm22116.entidades.Expediente;
import veterinaria.mm22116.interfaces.IExpedienteServicio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServicioExpediente implements IExpedienteServicio {

    String archivo;

    public ServicioExpediente(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public List<Expediente> ObtenerExpediente() {
        List<Expediente> LstExpedientes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                Expediente expediente = new Expediente(linea);

                LstExpedientes.add(expediente);
            }

        } catch (IOException e) {
        }

        return LstExpedientes;
    }

    @Override
    public List<Expediente> ObtenerExpedienteActivas() {
        List<Expediente> LstExpedientes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                Expediente expediente = new Expediente(linea);

                if (expediente.getActivo()) {
                    LstExpedientes.add(expediente);
                }
            }

        } catch (IOException e) {
        }

        return LstExpedientes;
    }

    @Override
    public boolean CrearExpediente(Expediente expediente) {
        try {
            List<Expediente> LstExpediente = this.ObtenerExpediente();
            int size = LstExpediente.size();
            int id = 1;

            if (size > 0) {
                id = LstExpediente.get(size - 1).getIdExpediente() + 1;
            }

            expediente.setIdExpediente(id);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo, true))) {
                writer.write(expediente.toString());
                writer.newLine();
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean ActualizarExpediente(Expediente expediente) {
        try {
            List<Expediente> LstExpedientes = this.ObtenerExpediente();

            for (int i = 0; i < LstExpedientes.size(); i++) {
                if (LstExpedientes.get(i).getIdExpediente() == expediente.getIdExpediente()) {
                    LstExpedientes.set(i, expediente);
                    break;
                }

                GuardarExpedientes(LstExpedientes);
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean EliminarExpediente(int expedienteId) {
        try {
            List<Expediente> LstExpedientes = this.ObtenerExpediente();

            for (int i = 0; i < LstExpedientes.size(); i++) {
                if (LstExpedientes.get(i).getIdExpediente() == expedienteId) {
                    Expediente expediente = LstExpedientes.get(i);
                    expediente.setActivo(false);
                    LstExpedientes.set(i, expediente);
                    break;
                }
            }

            GuardarExpedientes(LstExpedientes);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void GuardarExpedientes(List<Expediente> LstExpedientes) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo, false))) {
                for (Expediente expediente : LstExpedientes) {
                    writer.write(expediente.toString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
        }
    }

    @Override
    public List<Expediente> ObtenerExpedientePorIdPaciente(int idPaciente) {
        List<Expediente> LstExpedientes = new ArrayList<>();
        try {
            List<Expediente> lst = this.ObtenerExpedienteActivas();

            for (Expediente e : lst) {
                if (e.getIdPaciente() == idPaciente) {
                    LstExpedientes.add(e);
                }
            }

        } catch (Exception e) {
        }

        return LstExpedientes;
    }
}
