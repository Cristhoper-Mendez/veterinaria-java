package veterinaria.mm22116.main;

import java.time.LocalDate;
import java.util.List;
import veterinaria.mm22116.entidades.*;
import veterinaria.mm22116.servicios.*;

public class MM221162023Veterinaria {

    public static void main(String[] args) {
        // No usar comas ',' en los strings ya que provoca conflicto al hacer string la clase para almacenar los datos en el archivo de texto;
        // dependiendo mi tiempo libre cambiare el caracter para el split
        String directoryName = System.getProperty("user.dir");

//        probarTipoPacientes(directoryName);
        probarRaza(directoryName);
//        probarPacientes(directoryName);
//        probarCita(directoryName);
//        probarExpediente(directoryName);
//        probarUsuario(directoryName);
//        probarVacuna(directoryName);
    }

    public static void probarTipoPacientes(String directoryName) {
        String tipoMascotaFile = directoryName + "\\src\\main\\java\\veterinaria\\mm22116\\archivos\\TipoPaciente.txt";

        TipoPaciente tipoPaciente = new TipoPaciente();
        tipoPaciente.setIdTipoPaciente(1);
        tipoPaciente.setNombreTipoPaciente("Perro23");
        tipoPaciente.setActivo(true);

        ServicioTipoPaciente servicioTipoPaciente = new ServicioTipoPaciente(tipoMascotaFile);
        List<TipoPaciente> Lst = servicioTipoPaciente.ObtenerTipoPacientes();

        System.out.println("Size: " + Lst.size());
        servicioTipoPaciente.ActualizarTipoPaciente(tipoPaciente);

    }

    public static void probarRaza(String directoryName) {

        String razaFile = directoryName + "\\src\\main\\java\\veterinaria\\mm22116\\archivos\\Raza.txt";
        Raza raza = new Raza(0, "Bulldog", true);
        ServicioRaza servicioRaza = new ServicioRaza(razaFile);

        List<Raza> LstRazas = servicioRaza.ObtenerRazas();
        System.out.println("Total de Razas: " + LstRazas.size());

        for (Raza r : LstRazas) {
            System.out.println("Nombre: " + r.getNombreRaza());
        }

//        servicioRaza.CrearRaza(raza);

    }

    public static void probarPacientes(String directoryName) {

        String pacienteFile = directoryName + "\\src\\main\\java\\veterinaria\\mm22116\\archivos\\Paciente.txt";

        Paciente paciente = new Paciente();
        paciente.setNombrePaciente("Kaiser");
        paciente.setNombreDueño("Cristhoper");
        paciente.setEdadPaciente(4.5);
        paciente.setRazaId(1);
        paciente.setTipoPaciente(1);
        paciente.setFechaInscripcion(LocalDate.now());
        paciente.setSexo(true);
        paciente.setMedidas("40cm, 60cm");
        paciente.setNumeroIdentificacion(123);
        paciente.setPelajePaciente("nose xd");
        paciente.setFechaNacimiento(LocalDate.MIN);
        paciente.setActivo(true);

        ServicioPaciente servicioPaciente = new ServicioPaciente(pacienteFile);

        List<Paciente> Lst = servicioPaciente.ObtenerPacientes();
        System.out.println("Total: " + Lst.size());

//        servicioPaciente.CrearPaciente(paciente);
    }

    public static void probarCita(String directoryName) {
        String citaFile = directoryName + "\\src\\main\\java\\veterinaria\\mm22116\\archivos\\Cita.txt";

        Cita cita = new Cita();
        cita.setFechaCita(LocalDate.now().plusDays(2));
        cita.setIdPaciente(1);
        cita.setMotivo("No quiere comer");
        cita.setNombrePaciente("Kaiser");
        cita.setActivo(true);

        ServicioCita servicioCita = new ServicioCita(citaFile);

        List<Cita> Lst = servicioCita.ObtenerCitas();
        System.out.println("Total: " + Lst.size());

//        servicioCita.CrearCita(cita);
    }

    public static void probarExpediente(String directoryName) {
        String expedienteFile = directoryName + "\\src\\main\\java\\veterinaria\\mm22116\\archivos\\Expediente.txt";

        Expediente expediente = new Expediente();
        expediente.setIdVacuna(1);
        expediente.setIdPaciente(1);
        expediente.setFechaCita(LocalDate.now().plusDays(2));
        expediente.setMedicamentoRecetado("Acetaminofen");
        expediente.setInformacionAdicional("Tiene que regresar en una semana");
        expediente.setActivo(true);

        ServicioExpediente servicioExpediente = new ServicioExpediente(expedienteFile);

        List<Expediente> lst = servicioExpediente.ObtenerExpediente();

        System.out.println("Total:  " + lst.size());

//        servicioExpediente.CrearExpediente(expediente);
    }

    public static void probarUsuario(String directoryName) {
        String usuarioFile = directoryName + "\\src\\main\\java\\veterinaria\\mm22116\\archivos\\Usuario.txt";

        Usuario usuario = new Usuario();
        usuario.setNombres("Cristhoper");
        usuario.setApellidos("Mendez");
        usuario.setCorreo("asd@gmail.com");
        usuario.setPassword("asdasd");
        usuario.setActivo(true);

        ServicioUsuario servicioUsuario = new ServicioUsuario(usuarioFile);

        List<Usuario> lst = servicioUsuario.ObtenerUsuarios();
        System.out.println("Total: " + lst.size());

//        servicioUsuario.CrearUsuario(usuario);
    }

    public static void probarVacuna(String directoryName) {
        String vacunaFile = directoryName + "\\src\\main\\java\\veterinaria\\mm22116\\archivos\\Vacuna.txt";

        Vacunas vacuna = new Vacunas();
        vacuna.setNombreVacuna("dectomax");
        vacuna.setPeso(32.4);
        vacuna.setAltura(0.6);
        vacuna.setEdad(3.4);
        vacuna.setIdPaciente(1);
        vacuna.setActivo(true);

        ServicioVacunas servicioVacunas = new ServicioVacunas(vacunaFile);

        List<Vacunas> lst = servicioVacunas.ObtenerVacunas();
        System.out.println("Total:  " + lst.size());

//        servicioVacunas.CrearVacunas(vacuna);
    }
}
