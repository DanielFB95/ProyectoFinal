import 'package:flutter_proyecto_final/models/medicamento_response.dart';

abstract class MedicamentoRepository {
  Future<List<Medicamento>> fetchMedicamentos();
}
