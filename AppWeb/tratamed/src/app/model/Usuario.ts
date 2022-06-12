export interface Usuario{
    id?:number;
    nombre: string;
    apellidos: string;
    fechaNacimiento: string;
    email: string;
    dni?: string;
    telefono: number;
    direccion?:string;
    password?:string;
    password2?:string;
    avatar?: any;
}