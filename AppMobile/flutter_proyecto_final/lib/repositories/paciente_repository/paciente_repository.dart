import 'package:flutter_proyecto_final/models/paciente_response.dart';

abstract class PacienteRepository {
  Future<Paciente> fetchPaciente();
}
