import 'package:flutter_proyecto_final/models/medico_edit.dart';
import 'package:flutter_proyecto_final/models/medico_response.dart';
import 'package:flutter_proyecto_final/models/pacientes_response.dart';

abstract class MedicoRepository {
  Future<List<Paciente>> fetchPacientes();

  Future<Medico> editMedico(MedicoEdit medicoEdit);
}
