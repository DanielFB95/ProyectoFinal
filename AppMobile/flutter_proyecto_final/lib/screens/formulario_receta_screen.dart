import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/dto/receta_dto.dart';
import 'package:flutter_proyecto_final/models/especialidad_response.dart';
import 'package:flutter_proyecto_final/models/medico_edit.dart';
import 'package:flutter_proyecto_final/models/medico_response.dart';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/models/receta_response.dart';
import 'package:flutter_proyecto_final/models/usuario_response.dart';
import 'package:flutter_proyecto_final/repositories/medico_repository.dart/medico_repository.dart';
import 'package:flutter_proyecto_final/repositories/medico_repository.dart/medico_repository_impl.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository.dart';
import 'package:flutter_proyecto_final/repositories/paciente_repository/paciente_repository_impl.dart';
import 'package:flutter_proyecto_final/repositories/recetaRepository/receta_repository.dart';
import 'package:flutter_proyecto_final/repositories/recetaRepository/receta_repositoyr_impl.dart';
import 'package:flutter_proyecto_final/screens/menu_medico_screen.dart';
import 'package:flutter_proyecto_final/screens/profile_screen.dart';
import 'package:intl/intl.dart';

/*class FormularioRecetaScreen extends StatelessWidget {
  FormularioRecetaScreen({Key? key, required this.paciente}) : super(key: key);

  final Paciente paciente;
  final _formKey = GlobalKey<FormState>();
  TextEditingController _nombreController = TextEditingController();
  TextEditingController _apellidosController = TextEditingController();
  TextEditingController _telefonoController = TextEditingController();
  TextEditingController _direccionController = TextEditingController();
  TextEditingController dateinput = TextEditingController(); 

  late PacienteRepository pacienteRepository;
  late MedicoRepository medicoRepository;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromARGB(245, 245, 245, 245),
      body: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.only(top: 35, right: 275),
                  child: IconButton(
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    icon: const Icon(Icons.keyboard_return),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 60),
                  child: Center(
                      child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15),
                        child: TextFormField(
                          controller: _nombreController,
                          decoration: const InputDecoration(
                              suffixIconColor: Colors.white,
                              hintText: '   Nombre'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún nombre';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  )),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _apellidosController,
                          decoration: const InputDecoration(
                              suffixIconColor: Colors.white,
                              hintText: '   Apellidos'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún texto';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _telefonoController,
                          decoration: const InputDecoration(
                              suffixIcon: Icon(Icons.phone),
                              suffixIconColor: Colors.white,
                              hintText: '   Teléfono'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún teléfono';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _direccionController,
                          decoration: const InputDecoration(
                              suffixIcon: Icon(Icons.location_city),
                              suffixIconColor: Colors.white,
                              hintText: '   Dirección'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce alguna dirección.';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 36),
                  child: Center(
                    child: SizedBox(
                      width: 300,
                      child: GestureDetector(
                        onTap: () {
                          if (_formKey.currentState!.validate()) {
                            if (paciente.rol.contains("PACIENTE")) {
                              final pacienteEditado = Usuario(
                                  nombre: _nombreController.text,
                                  apellidos: _apellidosController.text,
                                  direccion: _direccionController.text,
                                  telefono: _telefonoController.text,
                                  dni: paciente.dni,
                                  email: paciente.email,
                                  fechaNacimiento: paciente.fechaNacimiento,
                                  rol: paciente.rol);
                              pacienteRepository = PacienteRepositoryImpl();
                              pacienteRepository.editPaciente(pacienteEditado);
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const ProfileScreen()));
                            } else {
                              final medicoEditado = MedicoEdit(
                                nombre: _nombreController.text,
                                apellidos: _apellidosController.text,
                                direccion: _direccionController.text,
                                telefono: _telefonoController.text,
                                dni: paciente.dni,
                                email: paciente.email,
                                especialidad: 1,
                                numColegiado: '222222222A',
                                password: 'daniel12',
                                password2: 'daniel12',
                              );
                              medicoRepository = MedicoRepositoryImpl();
                              medicoRepository.editMedico(medicoEditado);
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const ProfileScreen()));
                            }
                          }
                        },
                        child: Container(
                          width: MediaQuery.of(context).size.width,
                          margin: const EdgeInsets.only(
                              top: 30, left: 30, right: 30),
                          padding: const EdgeInsets.symmetric(
                              horizontal: 50, vertical: 20),
                          decoration: BoxDecoration(
                              border: Border.all(color: Colors.black, width: 2),
                              borderRadius: BorderRadius.circular(50)),
                          child: Text(
                            'Guardar'.toUpperCase(),
                            style: const TextStyle(color: Colors.black),
                            textAlign: TextAlign.center,
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
                TextField(
                controller: dateinput, //editing controller of this TextField
                decoration: InputDecoration( 
                   icon: Icon(Icons.calendar_today), //icon of text field
                   labelText: "Enter Date" //label text of field
                ),
                readOnly: true,  //set it true, so that user will not able to edit text
                onTap: () async {
                  DateTime pickedDate = await showDatePicker(
                      context: context, initialDate: DateTime.now(),
                      firstDate: DateTime(2000), //DateTime.now() - not to allow to choose before today.
                      lastDate: DateTime(2101)
                  );
                  
                  if(pickedDate != null ){
                      print(pickedDate);  //pickedDate output format => 2021-03-10 00:00:00.000
                      String formattedDate = DateFormat('yyyy-MM-dd').format(pickedDate); 
                      print(formattedDate); //formatted date output using intl package =>  2021-03-16
                        //you can implement different kind of Date Format here according to your requirement

                      setState(() {
                         dateinput.text = formattedDate; //set output date to TextField value. 
                      });
                  }else{
                      print("Date is not selected");
                  }
                },
             )
              ],
            ),
          )),
    );
  }
}*/

class FormularioRecetaScreen extends StatefulWidget {
  const FormularioRecetaScreen({Key? key, required this.paciente})
      : super(key: key);

  final Paciente paciente;

  @override
  State<FormularioRecetaScreen> createState() => _FormularioRecetaScreenState();
}

class _FormularioRecetaScreenState extends State<FormularioRecetaScreen> {
  final _formKey = GlobalKey<FormState>();
  TextEditingController _nombreController = TextEditingController();
  TextEditingController _apellidosController = TextEditingController();
  TextEditingController _telefonoController = TextEditingController();
  TextEditingController _direccionController = TextEditingController();
  TextEditingController fechaInicio = TextEditingController();
  List<String> diasDeTomas = ["LUNES", "MARTES"];
  List<String> momentosDeTomas = ["MAÑANA"];
  late RecetaRepository recetaRepository;

  @override
  void initState() {
    super.initState();
    recetaRepository = RecetaRepositoryImpl();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color.fromARGB(245, 245, 245, 245),
      body: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.only(top: 60),
                  child: Center(
                      child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15),
                        child: TextFormField(
                          controller: _nombreController,
                          decoration: const InputDecoration(
                              suffixIconColor: Colors.white,
                              hintText: '   Nombre'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún nombre';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  )),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _apellidosController,
                          decoration: const InputDecoration(
                              suffixIconColor: Colors.white,
                              hintText: '   Apellidos'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún texto';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _telefonoController,
                          decoration: const InputDecoration(
                              suffixIcon: Icon(Icons.phone),
                              suffixIconColor: Colors.white,
                              hintText: '   Teléfono'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce algún teléfono';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Card(
                    child: SizedBox(
                      width: 300,
                      child: Padding(
                        padding: const EdgeInsets.only(top: 15.0),
                        child: TextFormField(
                          controller: _direccionController,
                          decoration: const InputDecoration(
                              suffixIcon: Icon(Icons.location_city),
                              suffixIconColor: Colors.white,
                              hintText: '   Dirección'),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor introduce alguna dirección.';
                            }
                            return null;
                          },
                        ),
                      ),
                    ),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 36),
                  child: Center(
                    child: SizedBox(
                      width: 300,
                      child: GestureDetector(
                        onTap: () {
                          if (_formKey.currentState!.validate()) {
                            if (widget.paciente.rol.contains("PACIENTE")) {
                              final nuevaReceta = RecetaDto(
                                  fechaInicio: fechaInicio.text,
                                  fechaFin: fechaInicio.text,
                                  diasDeTomas: diasDeTomas,
                                  momentosDeTomas: momentosDeTomas,
                                  idMedicamento: "1",
                                  idPaciente: widget.paciente.id);
                              recetaRepository.nuevaReceta(nuevaReceta);
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const MenuMedicoScreen()));
                            } else {
                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          const ProfileScreen()));
                            }
                          }
                        },
                        child: Container(
                          width: MediaQuery.of(context).size.width,
                          margin: const EdgeInsets.only(
                              top: 30, left: 30, right: 30),
                          padding: const EdgeInsets.symmetric(
                              horizontal: 50, vertical: 20),
                          decoration: BoxDecoration(
                              border: Border.all(color: Colors.black, width: 2),
                              borderRadius: BorderRadius.circular(50)),
                          child: Text(
                            'Editar'.toUpperCase(),
                            style: const TextStyle(color: Colors.black),
                            textAlign: TextAlign.center,
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
                TextField(
                  controller:
                      fechaInicio, //editing controller of this TextField
                  decoration: InputDecoration(
                      icon: Icon(Icons.calendar_today), //icon of text field
                      labelText: "Fecha inicio" //label text of field
                      ),
                  readOnly:
                      true, //set it true, so that user will not able to edit text
                  onTap: () async {
                    DateTime? pickedDate = await showDatePicker(
                        context: context,
                        initialDate: DateTime.now(),
                        firstDate: DateTime(
                            2000), //DateTime.now() - not to allow to choose before today.
                        lastDate: DateTime(2101));

                    if (pickedDate != null) {
                      print(
                          pickedDate); //pickedDate output format => 2021-03-10 00:00:00.000
                      String formattedDate =
                          DateFormat('yyyy-MM-dd').format(pickedDate);
                      print(
                          formattedDate); //formatted date output using intl package =>  2021-03-16
                      //you can implement different kind of Date Format here according to your requirement

                      setState(() {
                        fechaInicio.text =
                            formattedDate; //set output date to TextField value.
                      });
                    } else {
                      print("Date is not selected");
                    }
                  },
                )
              ],
            ),
          )),
    );
  }
}
