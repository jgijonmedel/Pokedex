# Pokédex
Aplicación móvil para la plataforma Android que mediante una lista muestra los pokémons y su información, que han aperecido en varios juegos y animes de la saga Pokémon. 

 _Esta aplicación se creo para practicar el consumo de **APIs** con la librería **Retrofit**_
 
<br/>

## Contenido
1. [Información general](#información-general)
2. [Construido con](#construido-con)
3. [Interfaz de usuario](#interfaz-de-usuario)
4. [Demo](#demo)

</br>

## Información general
La aplicación es un ejemplo de tipo **master-detail** que cuenta con dos pantallas.

La primera en la que se muestra la lista de los diferentes pokémos que se obtienen a consultar la API publica [PokeApi](https://pokeapi.co/docs/v2), en dicha lista se puede realizar una búsqueda de pokémons escribiendo ya sea el nombre o numero en la barra de búsqueda ubicada en la parte superior de la pantalla.

Al seleccionar un pokémon de la lista se abrirá la segunda pantalla en la que se vera la información del pokémon elegido.

<br/>

## Construido con
- [MVVM pattern](#construido-con): es el lenguaje de programación recomendado por la comunidad de **Android developers** para desarrollar aplicaciones moviles para la pratadorma Android.
- [Kotlin](https://kotlinlang.org/docs/android-overview.html): es el lenguaje de programación recomendado por la comunidad de **Android developers** para desarrollar aplicaciones moviles para la pratadorma Android.
- [Retrofit](https://square.github.io/retrofit/): es una librería que simplifica las tareas de llamadas de red a una API.
- [Glide](https://bumptech.github.io/glide/): es una librería que permite la carga rapida de imagenes desde la red mediante url.
- [Jetpack Navigation Component](https://developer.android.com/guide/navigation): la navegación se refiere a las interacciones que permiten a los usuarios navegar, entrar y salir de las diferentes secciones de contenido dentro de su aplicación.
- [Figma](https://figma.com/): figma es un editor de gráficos vectoriales y una herramienta de creación de prototipos.

<br/>

## Interfaz de usuario
Por sobre todo se buscó que la interfaz gráfica de la aplicación fuera sencilla y minimalista, además que cuenta con tema claro y oscuro.

### Tema claro
---
<p float="center">
  <img src="/art/pokemon_list_light.png" width="22%" hspace="1%" />
  <img src="/art/pokemon_about_light.png" width="22%" hspace="1%" /> 
  <img src="/art/empty_list_light.png" width="22%" hspace="1%" />
  <img src="/art/no_internet_light.png" width="22%" hspace="1%" />
</p>

### Tema oscuro
---
<p float="center">
  <img src="/art/pokemon_list_dark.png" width="22%" hspace="1%" />
  <img src="/art/pokemon_about_dark.png" width="22%" hspace="1%" /> 
  <img src="/art/empty_list_dark.png" width="22%" hspace="1%" />
  <img src="/art/no_internet_dark.png" width="22%" hspace="1%" />
</p>

<br/>

## Demo
<p float="center">
  <img src="/art/search.gif" width="22%" hspace="1%" />
  <img src="/art/filter.gif" width="22%" hspace="1%" /> 
  <img src="/art/details.gif" width="22%" hspace="1%" />
</p>
