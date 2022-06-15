import 'package:flutter_proyecto_final/models/dto/receta_dto.dart';
import 'package:flutter_proyecto_final/models/receta_response.dart';

abstract class RecetaRepository {
  Future<Receta> nuevaReceta(RecetaDto recetaDto);
}
