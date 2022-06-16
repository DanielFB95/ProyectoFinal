class MedicoEdit {
  MedicoEdit({
    required this.nombre,
    required this.apellidos,
    required this.email,
    required this.numColegiado,
    required this.telefono,
    required this.dni,
    required this.direccion,
    required this.password,
    required this.password2,
    required this.especialidad,
  });
  late final String nombre;
  late final String apellidos;
  late final String email;
  late final String numColegiado;
  late final String telefono;
  late final String dni;
  late final String direccion;
  late final String password;
  late final String password2;
  late final int especialidad;

  MedicoEdit.fromJson(Map<String, dynamic> json) {
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    email = json['email'];
    numColegiado = json['numColegiado'];
    telefono = json['telefono'];
    dni = json['dni'];
    direccion = json['direccion'];
    password = json['password'];
    password2 = json['password2'];
    especialidad = json['especialidad'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['email'] = email;
    _data['numColegiado'] = numColegiado;
    _data['telefono'] = telefono;
    _data['dni'] = dni;
    _data['direccion'] = direccion;
    _data['password'] = password;
    _data['password2'] = password2;
    _data['especialidad'] = especialidad;
    return _data;
  }
}
