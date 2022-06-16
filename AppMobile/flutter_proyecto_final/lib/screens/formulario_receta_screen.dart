// ignore_for_file: unused_field, prefer_final_fields, prefer_const_constructors, avoid_print, unnecessary_this

import 'package:flutter/material.dart';
import 'package:flutter_proyecto_final/models/dto/receta_dto.dart';
import 'package:flutter_proyecto_final/models/medicamento_response.dart';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/repositories/medicamento_repository/medicamento_repository.dart';
import 'package:flutter_proyecto_final/repositories/medicamento_repository/medicamento_repository_impl.dart';
import 'package:flutter_proyecto_final/repositories/recetaRepository/receta_repository.dart';
import 'package:flutter_proyecto_final/repositories/recetaRepository/receta_repositoyr_impl.dart';
import 'package:flutter_proyecto_final/screens/menu_medico_screen.dart';
import 'package:intl/intl.dart';

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
  TextEditingController fechaFin = TextEditingController();
  List<String> diasDeTomas = [];
  List<String> momentosDeTomas = [];
  late RecetaRepository recetaRepository;
  late MedicamentoRepository medicamentoRepository;
  late Future<List<Medicamento>> listaMedicamentos;
  List<Medicamento> medicamentos = [];
  List<String> listaNombresMedicamentos = [];
  bool valueManiana = false;
  bool valueTarde = false;
  bool valueNoche = false;
  bool valueLunes = false;
  bool valueMartes = false;
  bool valueMiercoles = false;
  bool valueJueves = false;
  bool valueViernes = false;
  bool valueSabado = false;
  bool valueDomingo = false;
  String dropdownValue = 'Ibuprofeno';

  @override
  void initState() {
    super.initState();
    recetaRepository = RecetaRepositoryImpl();
    medicamentoRepository = MedicamentoRepositoryImpl();
    listaMedicamentos = medicamentoRepository.fetchMedicamentos();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.only(top: 45, right: 305, bottom: 15),
              child: IconButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                icon: const Icon(
                  Icons.keyboard_return,
                  size: 50,
                ),
                color: Color.fromARGB(255, 33, 170, 185),
              ),
            ),
            Center(
              child: Text(
                "FORMULARIO",
                style: TextStyle(
                    color: Color.fromARGB(255, 33, 170, 185),
                    fontSize: 35,
                    fontWeight: FontWeight.bold),
              ),
            ),
            Center(
              child: Text(
                "NUEVA RECETA",
                style: TextStyle(
                    color: Color.fromARGB(255, 33, 170, 185),
                    fontSize: 35,
                    fontWeight: FontWeight.bold),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 18.0),
              child: Form(
                  key: _formKey,
                  child: SingleChildScrollView(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Padding(
                            padding: const EdgeInsets.only(top: 5),
                            child: Container(
                              padding: EdgeInsets.only(left: 60),
                              width: MediaQuery.of(context).size.width / 1.08,
                              child: DropdownButton<String>(
                                value: dropdownValue,
                                icon: const Icon(Icons.arrow_downward),
                                elevation: 16,
                                style: const TextStyle(
                                    color: Color.fromARGB(255, 0, 0, 0)),
                                underline: Container(
                                  height: 2,
                                  color: Colors.deepPurpleAccent,
                                ),
                                onChanged: (String? newValue) {
                                  setState(() {
                                    dropdownValue = newValue!;
                                  });
                                },
                                items: <String>[
                                  'Ibuprofeno',
                                  'Paracetamol',
                                ].map<DropdownMenuItem<String>>((String value) {
                                  return DropdownMenuItem<String>(
                                    value: value,
                                    child: Text(value),
                                  );
                                }).toList(),
                              ),
                            )),
                        Padding(
                          padding: const EdgeInsets.all(20.0),
                          child: TextField(
                            controller: fechaInicio,
                            decoration: InputDecoration(
                                icon: Icon(Icons.calendar_today),
                                labelText: "Fecha inicio"),
                            readOnly: true,
                            onTap: () async {
                              DateTime? pickedDate = await showDatePicker(
                                  context: context,
                                  initialDate: DateTime.now(),
                                  firstDate: DateTime(2000),
                                  lastDate: DateTime(2101));

                              if (pickedDate != null) {
                                print(pickedDate);
                                String formattedDate =
                                    DateFormat('yyyy-MM-dd').format(pickedDate);
                                print(formattedDate);

                                setState(() {
                                  fechaInicio.text = formattedDate;
                                });
                              } else {
                                print("No se ha seleccionada una fecha");
                              }
                            },
                          ),
                        ),
                        Padding(
                          padding: const EdgeInsets.all(20.0),
                          child: TextField(
                            controller: fechaFin,
                            decoration: InputDecoration(
                                icon: Icon(Icons.calendar_today),
                                labelText: "Fecha fin"),
                            readOnly: true,
                            onTap: () async {
                              DateTime? pickedDate = await showDatePicker(
                                  context: context,
                                  initialDate: DateTime.now(),
                                  firstDate: DateTime(2000),
                                  lastDate: DateTime(2101));

                              if (pickedDate != null) {
                                print(pickedDate);
                                String formattedDate =
                                    DateFormat('yyyy-MM-dd').format(pickedDate);
                                print(formattedDate);

                                setState(() {
                                  fechaFin.text = formattedDate;
                                });
                              } else {
                                print("No se ha seleccionada una fecha");
                              }
                            },
                          ),
                        ),
                        Padding(
                          padding: const EdgeInsets.all(20.0),
                          child: Text(
                            'Momentos de tomas',
                            style: TextStyle(fontSize: 20.0),
                          ),
                        ),
                        CheckboxListTile(
                          secondary: const Icon(Icons.alarm),
                          title: const Text('Mañana'),
                          value: this.valueManiana,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueManiana = value!;
                              if (this.valueManiana) {
                                momentosDeTomas.add("MAÑANA");
                              } else {
                                momentosDeTomas.remove("MAÑANA");
                              }
                            });
                          },
                        ),
                        CheckboxListTile(
                          controlAffinity: ListTileControlAffinity.trailing,
                          secondary: const Icon(Icons.alarm),
                          title: const Text('Tarde'),
                          value: this.valueTarde,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueTarde = value!;
                              if (this.valueTarde) {
                                momentosDeTomas.add("TARDE");
                              } else {
                                momentosDeTomas.remove("TARDE");
                              }
                            });
                          },
                        ),
                        CheckboxListTile(
                          controlAffinity: ListTileControlAffinity.trailing,
                          secondary: const Icon(Icons.alarm),
                          title: const Text('Noche'),
                          value: this.valueNoche,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueNoche = value!;
                              if (this.valueNoche) {
                                momentosDeTomas.add("NOCHE");
                              } else {
                                momentosDeTomas.remove("NOCHE");
                              }
                            });
                          },
                        ),
                        Padding(
                          padding: const EdgeInsets.all(20.0),
                          child: Text(
                            'Días de tomas',
                            style: TextStyle(fontSize: 20.0),
                          ),
                        ),
                        CheckboxListTile(
                          secondary: const Icon(Icons.date_range),
                          title: const Text('Lunes'),
                          value: this.valueLunes,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueLunes = value!;
                              if (this.valueLunes) {
                                diasDeTomas.add("LUNES");
                              } else {
                                diasDeTomas.remove("LUNES");
                              }
                            });
                          },
                        ),
                        CheckboxListTile(
                          controlAffinity: ListTileControlAffinity.trailing,
                          secondary: const Icon(Icons.date_range),
                          title: const Text('Martes'),
                          value: this.valueMartes,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueMartes = value!;
                              if (this.valueMartes) {
                                diasDeTomas.add("MARTES");
                              } else {
                                diasDeTomas.remove("MARTES");
                              }
                            });
                          },
                        ),
                        CheckboxListTile(
                          controlAffinity: ListTileControlAffinity.trailing,
                          secondary: const Icon(Icons.date_range),
                          title: const Text('Miércoles'),
                          value: this.valueMiercoles,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueMiercoles = value!;
                              if (this.valueMiercoles) {
                                diasDeTomas.add("MIERCOLES");
                              } else {
                                diasDeTomas.remove("MIERCOLES");
                              }
                            });
                          },
                        ),
                        CheckboxListTile(
                          controlAffinity: ListTileControlAffinity.trailing,
                          secondary: const Icon(Icons.date_range),
                          title: const Text('Jueves'),
                          value: this.valueJueves,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueJueves = value!;
                              if (this.valueJueves) {
                                diasDeTomas.add("JUEVES");
                              } else {
                                diasDeTomas.remove("JUEVES");
                              }
                            });
                          },
                        ),
                        CheckboxListTile(
                          controlAffinity: ListTileControlAffinity.trailing,
                          secondary: const Icon(Icons.date_range),
                          title: const Text('Viernes'),
                          value: this.valueViernes,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueViernes = value!;
                              if (this.valueViernes) {
                                diasDeTomas.add("VIERNES");
                              } else {
                                diasDeTomas.remove("VIERNES");
                              }
                            });
                          },
                        ),
                        CheckboxListTile(
                          controlAffinity: ListTileControlAffinity.trailing,
                          secondary: const Icon(Icons.date_range),
                          title: const Text('Sábado'),
                          value: this.valueSabado,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueSabado = value!;
                              if (this.valueSabado) {
                                diasDeTomas.add("SABADO");
                              } else {
                                diasDeTomas.remove("SABADO");
                              }
                            });
                          },
                        ),
                        CheckboxListTile(
                          controlAffinity: ListTileControlAffinity.trailing,
                          secondary: const Icon(Icons.date_range),
                          title: const Text('Domingo'),
                          value: this.valueDomingo,
                          onChanged: (bool? value) {
                            setState(() {
                              this.valueDomingo = value!;
                              if (this.valueDomingo) {
                                diasDeTomas.add("DOMINGO");
                              } else {
                                diasDeTomas.remove("DOMINGO");
                              }
                            });
                          },
                        ),
                        Padding(
                          padding: const EdgeInsets.only(top: 10, bottom: 30),
                          child: Center(
                            child: SizedBox(
                              width: 300,
                              child: GestureDetector(
                                onTap: () {
                                  if (_formKey.currentState!.validate()) {
                                    final nuevaReceta = RecetaDto(
                                        fechaInicio: fechaInicio.text,
                                        fechaFin: fechaFin.text,
                                        diasDeTomas: diasDeTomas,
                                        momentosDeTomas: momentosDeTomas,
                                        idMedicamento: "2p",
                                        idPaciente: widget.paciente.id);
                                    recetaRepository.nuevaReceta(nuevaReceta);
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (context) =>
                                                MenuMedicoScreen()));
                                  }
                                },
                                child: Card(
                                  shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(150)),
                                  margin: EdgeInsets.all(15),
                                  elevation: 10,
                                  child: Container(
                                    width: MediaQuery.of(context).size.width,
                                    padding: const EdgeInsets.symmetric(
                                        horizontal: 50, vertical: 20),
                                    decoration: BoxDecoration(
                                        color:
                                            Color.fromARGB(255, 36, 175, 185),
                                        border: Border.all(
                                            color: Colors.red, width: 5),
                                        borderRadius:
                                            BorderRadius.circular(50)),
                                    child: Text(
                                      'Guardar'.toUpperCase(),
                                      style: const TextStyle(
                                          color: Colors.white,
                                          fontWeight: FontWeight.bold,
                                          fontSize: 20),
                                      textAlign: TextAlign.center,
                                    ),
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                  )),
            ),
          ],
        ),
      ),
    );
  }
}
