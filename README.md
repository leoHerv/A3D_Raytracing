## By HERVOUET Léo

# A3D Project

In this project I managed to make the raytracing works.  
I can create **planes** and/or **spheres** in a **scene**. I can choose the color of them, their 
reflection, shininess and their position.   
This project can compute **HDR** images, and it can compute images with **antialiasing**. 
Also, the main loop to compute pixels for the images is parallelized to be more efficient in the time of computation.
(A progress bar indicate the progression of the calculation of an image).  

The project don't have a makefile, and I'm sorry for that (I used intellij to compile and run the project).

## ==== Classes ====

### === Object ===
An **Object** have a position, a color, a specular color, a coefficient of reflection and a shininess.  
You can compute if there is an intersection with a ray, and also you can compute the normal for an intersection with this object.  

#### Plane :
A **Plane** is a basic object that represent a plane. It like an object, but you need to specify the normal for the plane.  

A **PlaneCheckerboard** is a plane with a second color to make a checkerboard pattern, and also you can specify the size of the pattern. 

#### Sphere :
A **Sphere** is an object that represent a sphere. It like an object but with a radius.   

A **Light** is a sphere that can be used to emit light. You can just specify the position, the color and the specular color.  
If the HDR is activated, the color of the lights are not changed, it just to see them better.

### === Color ===
Class to represent a color with blue, green, red and an alpha color. (The alpha color is not used in this project).

### === Main ===
The main class were the program is launch. It's where the main loop is, all the pixel for a given
scene is computed here. Is the static variables you can choose if the render image use HDR or no,
the size of the antialiasing grid for one pixel, and you can choose the number of bounces for a ray.

### === Ray ===
Simple class to represent a ray with a starting point and a direction.

### === Vec3D ===
Class to represent a 3D point in the space and to do various operations between them.

### === Scene ===
A **Scene** is a place to put and compute the color of objects.  
In a scene you have two lists, one of objects and the second one of lights.  
And you can choose the ambient color of the scene.  
For a ray you can compute a color, usefull to compute all the pixels of an image of the scene.

#### --- BallsScene ---
A Scene with multiple balls to show the reflection in them. Also, the scene have a blue sky.
The white spheres in the air are the lights.  

**Parameters** : ENABLE_HDR: false, ANTI_ALIASING_SAMPLES_NB = 8, REFLECTION_NB = 10.
![BallsScene HDR OFF](/res/BallsScene/BallsScene_HDR_OFF.png "BallsScene HDR OFF")

**Parameters** : ENABLE_HDR: true, ANTI_ALIASING_SAMPLES_NB = 8, REFLECTION_NB = 10.
![BallsScene HDR ON](/res/BallsScene/BallsScene_HDR_ON.png "BallsScene HDR ON")

#### --- BasicScene ---
A Scene in a box with walls of different colors. 3 balls are place to show what we can do with them.  
And this scene have two checkerboard plane to show that they can be placed in any directions.  
The white sphere in the air is the light of the scene.  

**Parameters** : ENABLE_HDR: false, ANTI_ALIASING_SAMPLES_NB = 8, REFLECTION_NB = 10.
![BasicScene HDR OFF](/res/BasicScene/BasicScene_HDR_OFF.png "BasicScene HDR OFF")

**Parameters** : ENABLE_HDR: true, ANTI_ALIASING_SAMPLES_NB = 8, REFLECTION_NB = 10.
![BasicScene HDR ON](/res/BasicScene/BasicScene_HDR_ON.png "BasicScene HDR ON")

#### --- MirrorScene ---
A Scene with two mirror plane in front of each-other (with balls between them) to show the reflection.  
The white spheres in the air are the lights.  

**Parameters** : ENABLE_HDR: false, ANTI_ALIASING_SAMPLES_NB = 8, REFLECTION_NB = 10.
![MirrorScene HDR OFF](/res/MirrorScene/MirrorScene_HDR_OFF.png "MirrorScene HDR OFF")

**Parameters** : ENABLE_HDR: true, ANTI_ALIASING_SAMPLES_NB = 8, REFLECTION_NB = 10.
![MirrorScene HDR ON](/res/MirrorScene/MirrorScene_HDR_ON.png "MirrorScene HDR ON")
