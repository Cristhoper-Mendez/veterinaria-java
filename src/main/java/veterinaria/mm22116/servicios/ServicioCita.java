package veterinaria.mm22116.servicios;

import java.util.List;
import java.util.ArrayList;
import veterinaria.mm22116.entidades.Cita;
import veterinaria.mm22116.interfaces.ICitaServicio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServicioCita implements ICitaServicio {

    String archivo;

    public ServicioCita(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public List<Cita> ObtenerCitas() {
        List<Cita> LstCita = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                Cita cita = new Cita(linea);

                LstCita.add(cita);
            }

        } catch (IOException e) {
        }
        return LstCita;
    }

    @Override
    public List<Cita> ObtenerCitasActivas() {
        List<Cita> LstCita = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.archivo));

            String linea;

            while ((linea = reader.readLine()) != null && !linea.equals("")) {
                Cita cita = new Cita(linea);

                if (cita.getActivo()) {
                    LstCita.add(cita);
                }

            }

        } catch (IOException e) {
        }
        return LstCita;
    }

    @Override
    public boolean CrearCita(Cita cita) {
        try {
            List<Cita> LstCitas = ObtenerCitas();
            int size = LstCitas.size();

            int id = 1;

            if (size > 0) {
                id = LstCitas.get(size - 1).getIdCita() + 1;
            }
            cita.setIdCita(id);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo))) {
                writer.write(cita.toString());
                writer.newLine();
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean ActualizarCita(Cita cita) {
        try {
            List<Cita> LstCitas = this.ObtenerCitas();

            for (int i = 0; i < LstCitas.size(); i++) {
                if (LstCitas.get(i).getIdCita() == cita.getIdCita()) {
                    LstCitas.set(i, cita);
                    break;
                }
            }

            GuardarCitas(LstCitas);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean EliminarCita(int idCita) {
        try {
            List<Cita> LstCitas = this.ObtenerCitas();

            for (int i = 0; i < LstCitas.size(); i++) {
                if (LstCitas.get(i).getIdCita() == idCita) {
                    Cita cita = LstCitas.get(i);
                    cita.setActivo(false);

                    LstCitas.set(i, cita);
                    break;
                }
            }

            GuardarCitas(LstCitas);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void GuardarCitas(List<Cita> LstCitas) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.archivo, false))) {
                for (Cita cita : LstCitas) {
                    writer.write(cita.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public List<Cita> ObtenerCitasPorPacienteId(int idPaciente) {
        List<Cita> LstCitasDelPaciente = new ArrayList<>();
        try {
            List<Cita> LstCitas = this.ObtenerCitasActivas();

            for (Cita c : LstCitas) {
                if (c.getIdPaciente() == idPaciente) {
                    LstCitasDelPaciente.add(c);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return LstCitasDelPaciente;
    }
}
