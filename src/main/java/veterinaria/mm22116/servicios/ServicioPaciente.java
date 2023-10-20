package veterinaria.mm22116.servicios;

import java.util.List;
import java.util.ArrayList;
import veterinaria.mm22116.entidades.Paciente;
import veterinaria.mm22116.interfaces.IPacienteServicio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServicioPaciente implements IPacienteServicio {

    String archivo;

    public ServicioPaciente(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public List<Paciente> ObtenerPacientes() {
        List<Paciente> LstPacientes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                Paciente paciente = new Paciente(linea);

                LstPacientes.add(paciente);

            }

        } catch (IOException e) {
        }

        return LstPacientes;
    }

    @Override
    public List<Paciente> ObtenerPacientesActivos() {
        List<Paciente> LstPacientes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                Paciente paciente = new Paciente(linea);

                if (paciente.getActivo()) {
                    LstPacientes.add(paciente);
                }
            }

        } catch (IOException e) {
        }

        return LstPacientes;
    }

    @Override
    public boolean CrearPaciente(Paciente paciente) {
        try {
            List<Paciente> LstPacientes = this.ObtenerPacientes();
            int size = LstPacientes.size();
            int id = 1;

            if (size > 0) {
                id = LstPacientes.get(size - 1).getIdPaciente() + 1;
            }

            paciente.setIdPaciente(id);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo))) {
                writer.write(paciente.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean ActualizarPaciente(Paciente paciente) {
        try {
            List<Paciente> LstPacientes = this.ObtenerPacientes();

            for (int i = 0; i < LstPacientes.size(); i++) {
                if (LstPacientes.get(i).getIdPaciente() == paciente.getIdPaciente()) {
                    LstPacientes.set(i, paciente);
                    break;
                }
            }

            GuardarPacientes(LstPacientes);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean EliminarPaciente(int pacienteId) {
        try {
            List<Paciente> LstPacientes = ObtenerPacientes();

            for (int i = 0; i < LstPacientes.size(); i++) {
                if (LstPacientes.get(i).getIdPaciente() == pacienteId) {
                    Paciente paciente = LstPacientes.get(i);
                    paciente.setActivo(false);
                    LstPacientes.set(i, paciente);

                    break;
                }
            }

            GuardarPacientes(LstPacientes);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private void GuardarPacientes(List<Paciente> LstPacientes) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo, false))) {
                for (Paciente paciente : LstPacientes) {
                    writer.write(paciente.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public Paciente ObtenerPacientePorId(int idPaciente) {
        Paciente p = null;
        List<Paciente> lstPacientes = this.ObtenerPacientesActivos();

        for (Paciente paciente : lstPacientes) {
            if (paciente.getIdPaciente() == idPaciente) {
                p = paciente;
//                break;
            }
        }

        return p;
    }
}
