class LoginResponse {
  LoginResponse({
    required this.nombre,
    required this.apellidos,
    this.telefono,
    required this.email,
    required this.token,
  });
  late final String nombre;
  late final String apellidos;
  late final Null telefono;
  late final String email;
  late final String token;

  LoginResponse.fromJson(Map<String, dynamic> json) {
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    telefono = null;
    email = json['email'];
    token = json['token'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['telefono'] = telefono;
    _data['email'] = email;
    _data['token'] = token;
    return _data;
  }
}
