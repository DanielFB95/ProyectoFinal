import 'package:flutter_proyecto_final/models/medico_response.dart';

class Paciente {
  Paciente({
    required this.id,
    required this.nombre,
    required this.apellidos,
    required this.fechaNacimiento,
    required this.avatar,
    required this.email,
    required this.telefono,
    required this.dni,
    required this.direccion,
    required this.rol,
    required this.observaciones,
    required this.medico,
  });
  late final String id;
  late final String nombre;
  late final String apellidos;
  late final String fechaNacimiento;
  late final String avatar;
  late final String email;
  late final String telefono;
  late final String dni;
  late final String direccion;
  late final String rol;
  late final String observaciones;
  late final Medico medico;

  Paciente.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    fechaNacimiento = json['fechaNacimiento'];
    avatar = json['avatar'];
    email = json['email'];
    telefono = json['telefono'];
    dni = json['dni'];
    direccion = json['direccion'];
    rol = json['rol'];
    observaciones = json['observaciones'];
    medico = Medico.fromJson(json['medico']);
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['fechaNacimiento'] = fechaNacimiento;
    _data['avatar'] = avatar;
    _data['email'] = email;
    _data['telefono'] = telefono;
    _data['dni'] = dni;
    _data['direccion'] = direccion;
    _data['rol'] = rol;
    _data['observaciones'] = observaciones;
    _data['medico'] = medico.toJson();
    return _data;
  }
}
