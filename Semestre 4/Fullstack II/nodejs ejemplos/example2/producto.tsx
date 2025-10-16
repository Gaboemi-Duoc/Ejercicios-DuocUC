export interface ProductoType {
  id: number;
  nombre: string;
  descripcion: string;
  precio: string;
  img: string;
  genero: string;
  tamano: string;
  jugadores: number;
  lanzamiento: string;
  desarrollador: string;
}

export const productos: ProductoType[] = [
  {
    id: 1,
    nombre: "Super Mario Wonder",
    descripcion:
      "Mario y compañia tienen una nueva aventura en Nintendo Switch",
    precio: "59.990",
    img: "/ui/producto/disparo6.jpg",
    genero: "Aventura",
    tamano: "Próximamente",
    jugadores: 1,
    lanzamiento: "Por confirmar",
    desarrollador: "Nintendo",
  },
  {
    id: 2,
    nombre: "Hollow Knight",
    descripcion: "Hallownest te espera para una aventura nunca antes vista",
    precio: "19.990",
    img: "/ui/producto/hollow k.jpg",
    genero: "Aventura / Metroidvania",
    tamano: "Próximamente",
    jugadores: 1,
    lanzamiento: "Por confirmar",
    desarrollador: "Team Cherry",
  },
];
