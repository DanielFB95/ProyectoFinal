import 'package:flutter_proyecto_final/models/pacientes_response.dart';

abstract class PacienteRepository {
  Future<List<Paciente>> fetchPacientes();
}
