package practica_clases;
import java.io.*;
import java.util.Scanner;
import java.util.List;

public class Practica_clases {
    private static Profesor[] profes;
    
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("     BIENVENIDO A LA ESCUELA ARTE GRANADA     ");
        System.out.println("==============================================");
        Scanner sc = new Scanner(System.in);
        System.out.println("Desea cargar los datos de la escuela? (si/no): ");
        String res = sc.nextLine();
        if (res.equalsIgnoreCase("si")) {
            System.out.println("Cargando datos...");

            int numAulas = obtenerNumeroDeAulas();
            Boolean[] aulas = new Boolean[numAulas];
            for (int i = 0; i < aulas.length; i++) {
                aulas[i] = false;
            }
            profes = new Profesor[numAulas];
            Asignaturas[] asig = new Asignaturas[numAulas*5];
            Alumno[] alum = new Alumno[numAulas*30];
            Curso[] cursos = new Curso[numAulas];
            boolean salir = false;

            cargarDatos(profes, alum, asig, cursos);

            while (!salir) {
                System.out.println("\n--- MENÚ ESCUELA ---");
                System.out.println("1. Opciones Alumno");
                System.out.println("2. Opciones Profesor");
                System.out.println("3. Opciones Curso");
                System.out.println("4. Opciones Asignatura");
                System.out.println("5. Opciones de presupuesto");
                System.out.println("6. Guardar datos y salir");
                System.out.println("7. Salir sin guardar datos");
                System.out.println("8. Cargar los datos de los archivos");
                System.out.print("Seleccione una opción: ");
                int opcion = sc.nextInt();
                int opcion2;
                sc.nextLine();
                
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- MENÚ ALUMNOS ---");
                        System.out.println("1. Agregar Alumno");
                        System.out.println("2. Eliminar Alumno");
                        System.out.println("3. Ver Alumnos");
                        System.out.println("4. Agregar Calificación");
                        System.out.println("5. Ver Boletín de Notas");
                        System.out.println("6. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                Alumno nuevoAlumno = CrearAlumno();
                                if (nuevoAlumno != null) {
                                    Rellenar(alum, nuevoAlumno);
                                    Matricular(nuevoAlumno, cursos);
                                }
                                break;
                            case 2: 
                                sc.nextLine();
                                System.out.println("Ingrese el DNI del Alumno a eliminar: ");
                                String d = sc.nextLine();
                                Eliminar(alum, d);
                                break;
                            case 3:
                                ImprimirAlumnos(alum);
                                break;
                            case 4:
                                sc.nextLine();
                                System.out.println("Ingrese el DNI del Alumno: ");
                                String dniAlumno = sc.nextLine();
                                Alumno alumnoCalif = null;
                                for (Alumno al : alum) {
                                    if (al != null && al.getDni().equals(dniAlumno)) {
                                        alumnoCalif = al;
                                        break;
                                    }
                                }
                                if (alumnoCalif != null) {
                                    agregarCalificacion(alumnoCalif);
                                } else {
                                    System.out.println("Alumno no encontrado.");
                                }
                                break;
                            case 5:
                                sc.nextLine();
                                System.out.println("Ingrese el DNI del Alumno: ");
                                dniAlumno = sc.nextLine();
                                alumnoCalif = null;
                                for (Alumno al : alum) {
                                    if (al != null && al.getDni().equals(dniAlumno)) {
                                        alumnoCalif = al;
                                        break;
                                    }
                                }
                                if (alumnoCalif != null) {
                                    mostrarBoletin(alumnoCalif);
                                } else {
                                    System.out.println("Alumno no encontrado.");
                                }
                                break;
                            case 6: 
                                break;  
                        }
                        break;
                    case 2:
                        System.out.println("\n--- MENÚ PROFESOR ---");
                        System.out.println("1. Agregar Profesor");
                        System.out.println("2. Delegar Profesor");
                        System.out.println("3. Eliminar Profesor");
                        System.out.println("4. Ver Profesores");
                        System.out.println("5. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                Profesor nuevoProf = CrearProfesor();
                                if (nuevoProf != null) {
                                    Rellenar(profes, nuevoProf);
                                }
                                break;
                            case 2: 
                                sc.nextLine();
                                System.out.println("Ingrese el DNI del Profesor que desea asignar: ");
                                String dn = sc.nextLine();
                                System.out.println("Ingrese el codigo de la Asignatura: ");
                                int codigo = sc.nextInt();
                                
                                // Verificar si es profesor auxiliar
                                Profesor prof = null;
                                for (Profesor p : profes) {
                                    if (p != null && p.getDni().equals(dn)) {
                                        prof = p;
                                        break;
                                    }
                                }
                                
                                if (prof instanceof ProfesorAuxiliar) {
                                    ProfesorAuxiliar aux = (ProfesorAuxiliar) prof;
                                    // Buscar la asignatura
                                    Asignaturas asignatura = null;
                                    for (Asignaturas a : asig) {
                                        if (a != null && a.getCodigo() == codigo) {
                                            asignatura = a;
                                            break;
                                        }
                                    }
                                    
                                    if (asignatura != null && aux.puedeAsignarAsignatura(asignatura)) {
                                        Asignar(asig, profes, dn, codigo);
                                    } else {
                                        System.out.println("El profesor auxiliar no puede ser asignado a esta asignatura.");
                                    }
                                } else {
                                    Asignar(asig, profes, dn, codigo);
                                }
                                break;
                            case 3:
                                sc.nextLine();
                                System.out.println("Ingrese el DNI del Profesor a eliminar");
                                String p = sc.nextLine();
                                Eliminar(profes, p);
                                break;
                            case 4: 
                                ImprimirProfesores(profes);
                                break;  
                            case 5:
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("\n--- MENÚ CURSO ---");
                        System.out.println("1. Crear Curso");
                        System.out.println("2. Ver Curso");
                        System.out.println("3. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                Curso c = CrearCurso(aulas, asig, alum, 1);
                                if (c == null) {
                                    System.out.println("creacion del curso abortada");
                                }
                                else{
                                    Rellenar(cursos, c);
                                    System.out.println("creacion del curso exitosa");
                                    System.out.println("Creacion del segundo año: ");
                                    Curso c2 = CrearCurso(aulas, asig, alum, 2);;
                                    if (c == null) {
                                        System.out.println("creacion del curso abortada");
                                    }
                                    else{
                                        Rellenar(cursos, c);
                                        System.out.println("creacion del curso exitosa");
                                    }
                                    break;
                                }
                            case 2: 
                                ImprimirCursos(cursos);
                                break;
                            case 3:
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("\n--- MENÚ ASIGNATURAS ---");
                        System.out.println("1. Crear Asignatura");
                        System.out.println("2. Eliminar Asignatura");
                        System.out.println("3. Ver Asignaturas");
                        System.out.println("4. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                    Rellenar(asig, CrearAsignatura());
                                break;
                            case 2: 
                                sc.nextLine();
                                System.out.println("Ingrese el codigo de la Asignatura a eliminar");
                                int a = sc.nextInt();
                                Eliminar(asig,a);
                                break;
                            case 3:
                                ImprimirAsignaturas(asig);
                                break;
                            case 4: 
                                break;  
                        }
                        break;
                    case 5:
                        System.out.println("\n--- MENÚ PRESUPUESTO ---");
                        System.out.println("1. Ver Ganancias de los cursos");
                        System.out.println("2. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                System.out.println("Ingrese los meses ha hacer el presupuesto de ganancias: ");
                                int m = sc.nextInt();
                                sc.nextLine();
                                if(Ganancias(cursos,m) == 0){
                                    System.out.println("No hay cursos para hacer un presupuesto acualmente");
                                }
                                else{
                                    System.out.println("Los beneficios brutos hasta el mes especificado son : "+ Ganancias(cursos,m));
                                    System.out.println("Las ganancias son: " + (Ganancias(cursos,m)-calcularGastos(cursos,m)));
                                }
                                break;
                            case 2: 
                                break;
                            case 3:
                                break;
                            case 4: 
                                break;  
                        }
                        break;
                    case 6:
                        guardarDatos(profes, alum, asig, cursos, numAulas);
                        System.out.println("Datos guardados. Saliendo...");
                        salir = true;
                        break;
                    case 7:
                        salir = true;
                        break;
                    case 8:
                        cargarDatos(profes, alum, asig, cursos);
                        System.out.println("Datos cargados correctamente.");
                        break;
                    default:
                        System.out.println("Opción no válida, intente de nuevo.");
                }
            }
            sc.close();
        } else {
            System.out.println("No se cargaran los datos");
            // Inicializar los arrays de objetos
            System.out.print("Ingrese el número de aulas en la escuela: ");
            int numAulas = sc.nextInt();
            sc.nextLine();
            Boolean[] aulas = new Boolean[numAulas];
            for (int i = 0; i < aulas.length; i++) {
                aulas[i] = false;
            }
            profes = new Profesor[numAulas];
            Asignaturas[] asig = new Asignaturas[numAulas*5];
            Alumno[] alum = new Alumno[numAulas*30];
            Curso[] cursos = new Curso[numAulas];
            boolean salir = false;

            // Menu principal
            while (!salir) {
                System.out.println("\n--- MENÚ ESCUELA ---");
                System.out.println("1. Opciones Alumno");
                System.out.println("2. Opciones Profesor");
                System.out.println("3. Opciones Curso");
                System.out.println("4. Opciones Asignatura");
                System.out.println("5. Opciones de presupuesto");
                System.out.println("6. Guardar datos y salir");
                System.out.println("7. Salir sin guardar datos");
                System.out.println("8. Cargar los datos de los archivos");
                System.out.print("Seleccione una opción: ");
                int opcion = sc.nextInt();
                int opcion2;
                sc.nextLine(); // Consumir el salto de línea
                
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- MENÚ ALUMNOS ---");
                        System.out.println("1. Agregar Alumno");
                        System.out.println("2. Eliminar Alumno");
                        System.out.println("3. Ver Alumnos");
                        System.out.println("4. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                Alumno nuevoAlumno = CrearAlumno();
                                if (nuevoAlumno != null) {
                                    Rellenar(alum, nuevoAlumno);
                                    Matricular(nuevoAlumno, cursos);
                                }
                                break;
                            case 2: 
                                sc.nextLine();
                                System.out.println("Ingrese el DNI del Alumno a eliminar: ");
                                String d = sc.nextLine();
                                Eliminar(alum, d);
                                break;
                            case 3:
                                ImprimirAlumnos(alum);
                                break;
                            case 4: 
                                break;  
                        }
                        break;
                    case 2:
                        System.out.println("\n--- MENÚ PROFESOR ---");
                        System.out.println("1. Agregar Profesor");
                        System.out.println("2. Delegar Profesor");
                        System.out.println("3. Eliminar Profesor");
                        System.out.println("4. Ver Profesores");
                        System.out.println("5. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                Rellenar(profes, CrearProfesor());
                                break;
                            case 2: 
                                    sc.nextLine();
                                    System.out.println("Ingrese el DNI del Profesor que desea asignar: ");
                                    String dn = sc.nextLine();
                                    System.out.println("Ingrese el codigo de la Asignatura: ");
                                    int codigo = sc.nextInt();
                                    Asignar(asig, profes, dn, codigo);
                                break;
                            case 3:
                                sc.nextLine();
                                System.out.println("Ingrese el DNI del Profesor a eliminar");
                                String p = sc.nextLine();
                                Eliminar(profes,p);
                                break;
                            case 4: 
                                ImprimirProfesores(profes);
                                break;  
                            case 5:
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("\n--- MENÚ CURSO ---");
                        System.out.println("1. Crear Curso");
                        System.out.println("2. Ver Curso");
                        System.out.println("3. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                Curso c = CrearCurso(aulas, asig, alum, 1);
                                if (c == null) {
                                    System.out.println("creacion del curso abortada");
                                }
                                else{
                                    Rellenar(cursos, c);
                                    System.out.println("creacion del curso exitosa");
                                    System.out.println("Creacion del segundo año: ");
                                    Curso c2 = CrearCurso(aulas, asig, alum, 2);;
                                    if (c == null) {
                                        System.out.println("creacion del curso abortada");
                                    }
                                    else{
                                        Rellenar(cursos, c);
                                        System.out.println("creacion del curso exitosa");
                                    }
                                    break;
                                }
                            case 2: 
                                ImprimirCursos(cursos);
                                break;
                            case 3:
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("\n--- MENÚ ASIGNATURAS ---");
                        System.out.println("1. Crear Asignatura");
                        System.out.println("2. Eliminar Asignatura");
                        System.out.println("3. Ver Asignaturas");
                        System.out.println("4. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                    Rellenar(asig, CrearAsignatura());
                                break;
                            case 2: 
                                sc.nextLine();
                                System.out.println("Ingrese el codigo de la Asignatura a eliminar");
                                int a = sc.nextInt();
                                Eliminar(asig,a);
                                break;
                            case 3:
                                ImprimirAsignaturas(asig);
                                break;
                            case 4: 
                                break;  
                        }
                        break;
                    case 5:
                        System.out.println("\n--- MENÚ PRESUPUESTO ---");
                        System.out.println("1. Ver Ganancias de los cursos");
                        System.out.println("2. Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion2 = sc.nextInt();
                        switch (opcion2) {
                            case 1:
                                System.out.println("Ingrese los meses ha hacer el presupuesto de ganancias: ");
                                int m = sc.nextInt();
                                sc.nextLine();
                                if(Ganancias(cursos,m) == 0){
                                    System.out.println("No hay cursos para hacer un presupuesto acualmente");
                                }
                                else{
                                    System.out.println("Los beneficios brutos hasta el mes especificado son : "+ Ganancias(cursos,m));
                                    System.out.println("Las ganancias son: " + (Ganancias(cursos,m)-calcularGastos(cursos,m)));
                                }
                                break;
                            case 2: 
                                break;
                            case 3:
                                break;
                            case 4: 
                                break;  
                        }
                        break;
                    case 6:
                        guardarDatos(profes, alum, asig, cursos, numAulas);
                        System.out.println("Datos guardados. Saliendo...");
                        salir = true;
                        break;
                    case 7:
                        salir = true;
                        break;
                    case 8:
                        cargarDatos(profes, alum, asig, cursos);
                        System.out.println("Datos cargados correctamente.");
                        break;
                    default:
                        System.out.println("Opción no válida, intente de nuevo.");
                }
            }
            sc.close();
        }

    }
    
    //Metodos para crear los objetos con las restricciones.
    public static Asignaturas CrearAsignatura(){
        Scanner sc = new Scanner(System.in);
        System.out.println("por favor ingrese el nombre:");
        String nombre = sc.nextLine();
        System.out.println("por favor ingrese el codigo:");
        int cod = sc.nextInt();
        System.out.println("por favor ingrese el numero de horas de la asignatura:");
        int h = sc.nextInt();
        Asignaturas asig = new Asignaturas(h,cod,nombre);
        System.out.println(asig.toString());
        return asig;
    }
    public static Profesor CrearProfesor(){
        Scanner sc = new Scanner(System.in);
        String dni, nombre;
        int sueldo;

        System.out.print("Ingrese DNI: ");
        dni = sc.nextLine();
        
        System.out.print("Ingrese Nombre: ");
        nombre = sc.nextLine();

        // Validación de sueldo
        do {
            System.out.print("Ingrese sueldo por hora (minimo 10€): ");
            sueldo = sc.nextInt();
            if (sueldo < 10) {
                System.out.println("ERROR: El sueldo debe ser al menos 10€/h.");
            }
        } while (sueldo < 10);

        System.out.println("¿Es profesor auxiliar? (si/no): ");
        sc.nextLine(); // Limpiar buffer
        String esAuxiliar = sc.nextLine();
        
        if (esAuxiliar.equalsIgnoreCase("si")) {
            System.out.println("Ingrese el DNI del profesor al que sustituirá: ");
            String dniSustituido = sc.nextLine();
            // Buscar el profesor a sustituir
            Profesor profesorSustituido = buscarProfesor(dniSustituido);
            if (profesorSustituido != null) {
                return new ProfesorAuxiliar(dni, nombre, profesorSustituido);
            } else {
                System.out.println("No se encontró el profesor a sustituir. Se creará como profesor regular.");
            }
        }

        // Crear profesor regular
        Profesor profe = new Profesor(dni, nombre, sueldo);
        System.out.println("Profesor creado correctamente.");
        return profe;
    }
    public static Alumno CrearAlumno(){
        Scanner sc = new Scanner(System.in);
        String dni, nombre, apellidos, tipoPago;
        int año;
        
        System.out.println("Ingrese el nombre del alumno: ");
        nombre = sc.nextLine();
        
        System.out.println("Ingrese el DNI del alumno: ");
        dni = sc.nextLine();
        
        System.out.println("Ingrese el apellido del alumno");
        apellidos = sc.nextLine();
        
        System.out.println("Ingrese el año de su alumno: ");
        año = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        
        do {
            System.out.println("Ingrese el tipo de pago del alumno (1/Completo 2/Plazos)");
            System.out.println("Para seleccionar ingrese (1) o (2)");
            int trig = sc.nextInt();
            
            if (trig == 1) {
                return new AlumnoPUnico(dni, nombre, apellidos, año);
            }
            else if (trig == 2) {
                return new AlumnoPPlazos(dni, nombre, apellidos, año);
            }
            else {
                System.out.println("ERROR: Opción inválida, intente nuevamente: ");
                System.out.println("");
            }
        } while (true);
    }
    
    // Método auxiliar para buscar un profesor por DNI
    private static Profesor buscarProfesor(String dni) {
        // Aquí deberías implementar la lógica para buscar en tu array de profesores
        // Este es solo un ejemplo, ajústalo según tu implementación
        for (Profesor p : profes) {
            if (p != null && p.getDni().equals(dni)) {
                return p;
            }
        }
        return null;
    }

    // Nuevo método para agregar calificaciones
    public static void agregarCalificacion(Alumno alumno) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el código de la asignatura: ");
        int codigoAsignatura = sc.nextInt();
        
        System.out.println("Ingrese la nota (0-10): ");
        double nota = sc.nextDouble();
        
        if (nota >= 0 && nota <= 10) {
            alumno.agregarCalificacion(codigoAsignatura, nota);
            System.out.println("Calificación agregada correctamente.");
        } else {
            System.out.println("Error: La nota debe estar entre 0 y 10.");
        }
    }

    // Método para mostrar el boletín de notas
    public static void mostrarBoletin(Alumno alumno) {
        System.out.println("\n=== BOLETÍN DE NOTAS ===");
        System.out.println("Alumno: " + alumno.getNombre() + " " + alumno.getApellidos());
        System.out.println("DNI: " + alumno.getDni());
        System.out.println("Año: " + alumno.getAño());
        System.out.println("\nCalificaciones:");
        
        List<Calificacion> calificaciones = alumno.getCalificaciones();
        if (calificaciones.isEmpty()) {
            System.out.println("No hay calificaciones registradas.");
        } else {
            for (Calificacion cal : calificaciones) {
                System.out.println("Asignatura " + cal.getCodigoAsignatura() + ": " + cal.getNota());
            }
        }
    }

public static Curso CrearCurso(Boolean[] aula, Asignaturas[] asig, Alumno[] alumnos, int año) {
    Scanner sc = new Scanner(System.in);
    int contAsig = 0;
    int contAlum = 0;
    boolean seleccion = false;
    
    System.out.println("Ingrese el nombre del curso: ");
    String nom = sc.nextLine();
    
    // Comprobación de aula válida
    int sala;
    do {
        System.out.println("Ingrese el número de su sala (empezando desde el 0): ");
        System.out.println("Ingrese -1 para salir del menu de creación de Curso");
        sala = sc.nextInt();
        if (sala == -1) {
            return null;
        }
        if (sala < 0 || sala >= aula.length) {
            System.out.println("Número de aula inválido. Intente nuevamente.");
        } else if (aula[sala] == true) {
            System.out.println("La sala ya está ocupada. Elija otra sala.");
        } else {
            aula[sala] = true;
            System.out.println("Sala seleccionada correctamente.");
            seleccion = true;
        }
    } while (!seleccion);

    // Obtener matrícula
    System.out.println("Ingrese el costo de la matrícula: ");
    int matricula = sc.nextInt();
    sc.nextLine(); // Limpiar el buffer del scanner
    
    // Comprobar asignaturas
    Asignaturas[] a = new Asignaturas[5];
    do {
        System.out.println("Ingrese el código de la asignatura (Ingrese -1 para salir):");
        int cod = sc.nextInt();
        sc.nextLine();
        if (cod == -1) {
            return null;
        }
        boolean asignada = false;
        for (int j = 0; j < asig.length; j++) {
            if (asig[j] != null && asig[j].Coincide(cod)) {
                if (asig[j].getProfesor() == null) {
                    System.out.println("Esta asignatura no tiene un profesor asignado.");
                } else {
                    a[contAsig] = asig[j];
                    contAsig++;
                    asignada = true;
                    System.out.println("Asignatura designada exitosamente.");
                    break;
                }
            }
        }
        if (!asignada) {
            System.out.println("Código de asignatura no válido. Intente nuevamente.");
        }
    } while (contAsig < 5); // Se deben asignar al menos 5 asignaturas

    // Comprobar alumnos
    Alumno[] alu = new Alumno[30];
    do {
        System.out.println("Ingrese el DNI del estudiante ('No' para no ingresar más estudiantes):");
        String dni = sc.nextLine();
        if (dni.equalsIgnoreCase("No")) {
            return null;
        }
        boolean agregado = false;
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] != null && alumnos[i].Coincide(dni)) {
                alu[contAlum] = alumnos[i];
                contAlum++;
                agregado = true;
                System.out.println("Alumno agregado exitosamente.");
                break;
            }
        }
        if (!agregado) {
            System.out.println("DNI no encontrado. Intente nuevamente.");
        }

        // Si se alcanzan 30 alumnos, preguntar si se quiere crear un curso extra
        if (contAlum == 30) {
            System.out.println("Se ha alcanzado el máximo de 30 alumnos. ¿Desea crear un nuevo curso? (Si/No)");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("Si")) {
                return CrearCurso(aula, asig, alumnos, año); // Crear un curso extra
            }
        }
    } while (contAlum < 10); // Se deben ingresar al menos 10 alumnos

    // Crear y retornar el curso
    Curso curso = new Curso(nom, a, alu, año, sala, matricula);
    return curso;
}


//Metodo para recibir un alumno y un curso y agregarlos

public static void Matricular(Alumno a, Curso[] c){
    Scanner sc = new Scanner(System.in);
    if (c[0] == null) {
        System.out.println("No hay cursos para matricular al estudiante en este momento");
        System.out.println("Se creara el estudiante y despues podra matricularlo manualmente");
    }
    else{
        System.out.println("Ingrese el nombre curso al que desea matricular el estudiante");
        String nom = sc.nextLine();
        for (int i = 0; i < c.length; i++) {
            if (c[i].getNombre().equals(nom)) {
                AgregarAlumno(a,c[i]);
            }
        }
    }
}


//Metodo para sacar las ganancias brutas de la escuela

public static void AgregarAlumno(Alumno a, Curso c){
    for (int i = 0; i < c.getAlumnos().length; i++) {
        if (c.getAlumnos()[i] == null) {
            c.getAlumnos()[i] = a;
            System.out.println("El alumno ha sido agregado correctamente");
            break;
        }
    }
}

    public static int Ganancias(Curso[] curso, int num_meses) {
        if (num_meses < 1 || num_meses > 9) {
            System.out.println("Número de meses inválido. Debe estar entre 1 y 9.");
            return 0;
        }
        int ganancias = 0;
        for (int i = 0; i < curso.length; i++) {
            if (curso[i] != null && curso[i].getAlumnos() != null) {
                Alumno[] a = curso[i].getAlumnos();
                int mensualidad = curso[i].getMatricula() / 9;
                for (int j = 0; j < a.length; j++) {
                    if (a[j] != null) {
                        if ("Completo".equals(a[j].getPago())) {
                            ganancias += curso[i].getMatricula();
                        } else if ("Plazos".equals(a[j].getPago())) {
                            ganancias += mensualidad * num_meses;
                        }
                    }
                }
            }
        }
        return ganancias;
    }
    
    //Metodo para sacar los gastos netos de la escuela
    
    public static int calcularGastos(Curso[] cursos, int num_meses) {
        int gastos = 0;

        for (int i = 0; i < cursos.length; i++) {
            if (cursos[i] != null) {
                for (int j = 0; j < cursos[i].getAsig().length; j++) {
                    Asignaturas a = cursos[i].getAsig()[j];
                    // Obtener el salario por hora y las horas de trabajo del profesor
                    int salarioPorHora = a.getProfesor().getSueldo_hora();
                    int horasTrabajo = a.getProfesor().getHoras_trabajo();

                    // Calculamos el gasto mensual para ese profesor
                    double salarioMensual = salarioPorHora * horasTrabajo * 4; // 4 semanas por mes
                    gastos += salarioMensual * num_meses; // Multiplicamos por los meses
                }
            }
        }
        return gastos;
    }

    //Metodo para asignar un profesor a una asignatura 
    
    public static void Asignar(Asignaturas[] asig, Profesor[] profe, String dni, int cod){
        for (int i = 0; i < profe.length ; i++) {
            if (profe[i] != null) {
                if (profe[i].Coincide(dni)) {
                    for (int j = 0; j < asig.length; j++) {
                        if (asig[j] != null) {
                            if (asig[j].Coincide(cod)) {
                               asig[j].setProfesor(profe[i]);
                               int h = asig[j].getNumero_horas();
                               int ht = h + profe[i].getHoras_trabajo();
                               profe[i].setHoras_trabajo(ht);
                               System.out.println("Profesor designado exitosamente");
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    //Metodo para encontrar espacios en los arrays
    
    public static void Rellenar(Alumno[] alumnos, Alumno al){
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] == null) {
                alumnos[i] = al;
                break;
            }
        }
    }
    
    public static void Rellenar(Profesor[] p, Profesor profe){
        for (int i = 0; i < p.length; i++) {
            if (p[i] == null) {
                p[i] = profe;
                break;
            }
        }
    }
    
    public static void Rellenar(Asignaturas[] a, Asignaturas asig){
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null) {
                a[i] = asig;
                break;
            }
        }
    }
    
    public static void Rellenar(Curso[] c, Curso curso){
        for (int i = 0; i < c.length; i++) {
            if (c[i] == null) {
                c[i] = curso;
                break;
            }
        }
    }
    
    
    //Metodos de eliminacion de elementos
    
    public static void Eliminar(Alumno[] alumnos, String dni){
        for (int i = 0; i < alumnos.length ; i++) {
            if (alumnos[i] != null) {
                if (alumnos[i].Coincide(dni)) {
                    alumnos[i] = null;
                    System.out.println("Alumno eliminado exitosamente");
                }
            }
        }
    }
    public static void Eliminar(Profesor[] p, String dni){
        for (int i = 0; i < p.length ; i++) {
            if (p[i] != null) {
                if (p[i].Coincide(dni)) {
                    p[i] = null;
                    System.out.println("Profesor eliminado exitosamente");
                }
            }
        }
    }
    public static void Eliminar(Asignaturas[] a, int cod){
        for (int i = 0; i < a.length ; i++) {
            if (a[i] != null) {
                if (a[i].Coincide(cod)) {
                    a[i] = null;
                    System.out.println("Asignatura eliminada exitosamente");
                }
            }
        }
    }
    
    //Metodos para imprimir los arrays de objetos

    public static void ImprimirAlumnos(Alumno[] alumnos) {
        System.out.println("\n--- LISTA DE ALUMNOS ---");
        boolean hayAlumnos = false;
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] != null) { 
                System.out.println(alumnos[i].toString());
                hayAlumnos = true;
            }
        }

        if (!hayAlumnos) {
            System.out.println("No hay alumnos registrados.");
        }
    }
    
    public static void ImprimirProfesores(Profesor[] profes) {
        System.out.println("\n--- LISTA DE PROFESORES ---");
        boolean hayProfes = false; 
        for (int i = 0; i < profes.length; i++) {
            if (profes[i] != null) {
                System.out.println(profes[i].toString());
                hayProfes = true;
            }
        }

        if (!hayProfes) {
            System.out.println("No hay profesores registrados.");
        }
    }
    
    public static void ImprimirAsignaturas(Asignaturas[] asig) {
        System.out.println("\n--- LISTA DE ASIGNATURAS ---");
        boolean hayProfes = false;
        for (int i = 0; i < asig.length; i++) {
            if (asig[i] != null) {
                System.out.println(asig[i].toString());
                hayProfes = true;
            }
        }

        if (!hayProfes) {
            System.out.println("No hay asignaturas registradas.");
        }
    }
    
    public static void ImprimirCursos(Curso[] cursos) {
        if (cursos == null || cursos.length == 0) {
            System.out.println("No hay cursos disponibles.");
            return;
        }
        else{
            System.out.println("Listado de Cursos:");
            for (Curso curso : cursos) {
                if (curso != null) {
                    curso.imprimirCurso();
                    System.out.println("-----------------------------");
                }
            }
        }
    }
    
        // Método para guardar datos
        public static void guardarDatos(Profesor[] profesores, Alumno[] alumnos, Asignaturas[] asignaturas, Curso[] cursos, int numAulas) {
            File carpeta = new File("Arch");
            if (!carpeta.exists()) {
                if (carpeta.mkdir()) {
                    System.out.println("Carpeta 'Arch' creada exitosamente.");
                } else {
                    System.out.println("Error al crear la carpeta 'Arch'. No se pueden guardar los datos.");
                    return;
                }
            }

            File fProfesores = new File("Arch/ficheroProfesores.txt");
            File fAlumnos = new File("Arch/ficheroAlumnos.txt");
            File fAsignaturas = new File("Arch/ficheroAsignaturas.txt");
            File fCursos = new File("Arch/ficheroCursos.txt");
            File fCalificaciones = new File("Arch/ficheroCalificaciones.txt");

            try {
                if (!fProfesores.exists()) fProfesores.createNewFile();
                if (!fAlumnos.exists()) fAlumnos.createNewFile();
                if (!fAsignaturas.exists()) fAsignaturas.createNewFile();
                if (!fCursos.exists()) fCursos.createNewFile();
                if (!fCalificaciones.exists()) fCalificaciones.createNewFile();

                // Guardar profesores
                FileWriter fwProfesores = new FileWriter(fProfesores);
                for (Profesor profesor : profesores) {
                    if (profesor != null) {
                        if (profesor instanceof ProfesorAuxiliar) {
                            ProfesorAuxiliar aux = (ProfesorAuxiliar) profesor;
                            fwProfesores.write("AUX:" + profesor.getDni() + ":" + profesor.getNombre() + ":" +
                                    profesor.getSueldo_hora() + ":" + profesor.getHoras_trabajo() + ":" +
                                    aux.getProfesorSustituido().getDni() + "\n");
                        } else {
                            fwProfesores.write("REG:" + profesor.getDni() + ":" + profesor.getNombre() + ":" +
                                    profesor.getSueldo_hora() + ":" + profesor.getHoras_trabajo() + "\n");
                        }
                    }
                }
                fwProfesores.close();

                // Guardar alumnos
                FileWriter fwAlumnos = new FileWriter(fAlumnos);
                FileWriter fwCalificaciones = new FileWriter(fCalificaciones);
                
                for (Alumno alumno : alumnos) {
                    if (alumno != null) {
                        String tipo = (alumno instanceof AlumnoPUnico) ? "UNICO" : "PLAZOS";
                        fwAlumnos.write(tipo + ":" + alumno.getDni() + ":" + alumno.getNombre() + ":" +
                                alumno.getApellidos() + ":" + alumno.getAño() + "\n");
                        
                        // Guardar calificaciones
                        for (Calificacion cal : alumno.getCalificaciones()) {
                            fwCalificaciones.write(alumno.getDni() + ":" + 
                                    cal.getCodigoAsignatura() + ":" + 
                                    cal.getNota() + "\n");
                        }
                    }
                }
                fwAlumnos.close();
                fwCalificaciones.close();

                // Guardar asignaturas
                FileWriter fwAsignaturas = new FileWriter(fAsignaturas);
                for (Asignaturas asignatura : asignaturas) {
                    if (asignatura != null) {
                        fwAsignaturas.write(asignatura.getCodigo() + ":" + asignatura.getNombre() + ":" +
                                (asignatura.getProfesor() != null ? asignatura.getProfesor().getDni() : "") + ":" +
                                asignatura.getNumero_horas() + "\n");
                    }
                }
                fwAsignaturas.close();

                // Guardar cursos
                FileWriter fwCursos = new FileWriter(fCursos, false);
                fwCursos.write(numAulas + "\n");
                for (Curso curso : cursos) {
                    if (curso != null) {
                        fwCursos.write(curso.volcado() + "\n");
                    }
                }
                fwCursos.close();

                System.out.println("Datos guardados correctamente.");
            } catch (IOException e) {
                System.out.println("Error al guardar los datos: " + e.getMessage());
            }
        }

        public static void cargarDatos(Profesor[] profesores, Alumno[] alumnos, Asignaturas[] asignaturas, Curso[] cursos) {
            try {
                // Cargar profesores
                BufferedReader brProfesores = new BufferedReader(new FileReader("Arch/ficheroProfesores.txt"));
                String linea;
                int index = 0;
                while ((linea = brProfesores.readLine()) != null) {
                    String[] partes = linea.split(":");
                    if (partes[0].equals("AUX")) {
                        // Buscar el profesor sustituido
                        Profesor sustituido = null;
                        for (Profesor p : profesores) {
                            if (p != null && p.getDni().equals(partes[5])) {
                                sustituido = p;
                                break;
                            }
                        }
                        if (sustituido != null) {
                            profesores[index] = new ProfesorAuxiliar(partes[1], partes[2], sustituido);
                        }
                    } else {
                        profesores[index] = new Profesor(partes[1], partes[2], Integer.parseInt(partes[3]));
                    }
                    profesores[index].setHoras_trabajo(Integer.parseInt(partes[4]));
                    index++;
                }
                brProfesores.close();
        
                // Cargar alumnos
                BufferedReader brAlumnos = new BufferedReader(new FileReader("Arch/ficheroAlumnos.txt"));
                index = 0;
                while ((linea = brAlumnos.readLine()) != null) {
                    String[] partes = linea.split(":");
                    if (partes[0].equals("UNICO")) {
                        alumnos[index] = new AlumnoPUnico(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]));
                    } else {
                        alumnos[index] = new AlumnoPPlazos(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]));
                    }
                    index++;
                }
                brAlumnos.close();
        
                // Cargar calificaciones
                BufferedReader brCalificaciones = new BufferedReader(new FileReader("Arch/ficheroCalificaciones.txt"));
                while ((linea = brCalificaciones.readLine()) != null) {
                    String[] partes = linea.split(":");
                    String dniAlumno = partes[0];
                    int codigoAsignatura = Integer.parseInt(partes[1]);
                    double nota = Double.parseDouble(partes[2]);
                    
                    // Buscar el alumno y agregar la calificación
                    for (Alumno alumno : alumnos) {
                        if (alumno != null && alumno.getDni().equals(dniAlumno)) {
                            alumno.agregarCalificacion(codigoAsignatura, nota);
                            break;
                        }
                    }
                }
                brCalificaciones.close();
        
                // Cargar asignaturas
                BufferedReader brAsignaturas = new BufferedReader(new FileReader("Arch/ficheroAsignaturas.txt"));
                index = 0;
                while ((linea = brAsignaturas.readLine()) != null) {
                    String[] partes = linea.split(":");
                    asignaturas[index++] = new Asignaturas(Integer.parseInt(partes[3]), Integer.parseInt(partes[0]), partes[1]);
                    if (!partes[2].isEmpty()) {
                        for (Profesor profesor : profesores) {
                            if (profesor != null && profesor.getDni().equals(partes[2])) {
                                asignaturas[index - 1].setProfesor(profesor);
                                break;
                            }
                        }
                    }
                }
                brAsignaturas.close();
        
                // Cargar cursos
                BufferedReader brCursos = new BufferedReader(new FileReader("Arch/ficheroCursos.txt"));
                String basura = brCursos.readLine();
                index = 0;
                while ((linea = brCursos.readLine()) != null) {
                    String[] partes = linea.split("-");
                    int año = Integer.parseInt(partes[0]);
                    String nombre = partes[1];

                    String[] asignaturasCodigos = partes[2].split(":");
                    Asignaturas[] cursoAsignaturas = new Asignaturas[5];
                    for (int i = 0; i < asignaturasCodigos.length; i++) {
                        if (!asignaturasCodigos[i].isEmpty()) {
                            for (Asignaturas asignatura : asignaturas) {
                                if (asignatura != null && asignatura.getNombre().equals(asignaturasCodigos[i])) {
                                    cursoAsignaturas[i] = asignatura;
                                    break;
                                }
                            }
                        }
                    }

                    String[] alumnosDnis = partes[3].split(":");
                    Alumno[] cursoAlumnos = new Alumno[30];
                    for (int i = 0; i < alumnosDnis.length; i++) {
                        if (!alumnosDnis[i].isEmpty()) {
                            for (Alumno alumno : alumnos) {
                                if (alumno != null && alumno.getDni().equals(alumnosDnis[i])) {
                                    cursoAlumnos[i] = alumno;
                                    break;
                                }
                            }
                        }
                    }

                    int aula = Integer.parseInt(partes[4]);
                    int matricula = Integer.parseInt(partes[5]);

                    cursos[index++] = new Curso(nombre, cursoAsignaturas, cursoAlumnos, año, aula, matricula);
                }
                brCursos.close();
        
                System.out.println("Datos cargados correctamente.");
            } catch (IOException e) {
                System.out.println("Error al cargar los datos: " + e.getMessage());
            }
        }

        // Método para obtener el número de aulas
        public static int obtenerNumeroDeAulas() {
            // Ruta relativa del archivo
            String rutaArchivo = "Arch/ficheroCursos.txt"; // Asegúrate de que el nombre del archivo sea correcto
            File archivoCursos = new File(rutaArchivo);
        
            // Verificar que el archivo exista
            if (archivoCursos.exists() && archivoCursos.isFile()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(archivoCursos))) {
                    // Leer la primera línea del archivo
                    String primeraLinea = reader.readLine();
                    if (primeraLinea != null && !primeraLinea.isEmpty()) {
                        return Integer.parseInt(primeraLinea.trim());
                    } else {
                        System.out.println("El archivo ficheroCursos.txt está vacío o mal formateado.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: La primera línea del archivo no es un número válido.");
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo ficheroCursos.txt: " + e.getMessage());
                }
            } else {
                System.out.println("El archivo 'ficheroCursos.txt' no existe en la carpeta 'Arch'.");
            }
        
            // Valor predeterminado si no se puede obtener el número de aulas
            return 0;
        }
    
}

