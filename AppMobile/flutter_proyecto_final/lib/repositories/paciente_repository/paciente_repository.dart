import 'package:flutter_proyecto_final/models/pacientes_response.dart';
import 'package:flutter_proyecto_final/models/receta_response.dart';
import 'package:flutter_proyecto_final/models/usuario_response.dart';

abstract class PacienteRepository {
  Future<List<Paciente>> fetchPacientes();

  Future<List<Receta>> fetchRecetas();

  Future<Paciente> editPaciente(Usuario usuario);
}
