package lab4.Scenes;

import lab1.DataStructures.Camera;
import lab1.DataStructures.Primitive;
import lab4.Light.AmbientLightSource;
import lab4.Light.DirectedLightSource;
import lab4.Light.PointLightSource;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private String name;
    private Camera camera;
    private List<PointLightSource> pointLights;
    private List<DirectedLightSource> directedLights;
    private AmbientLightSource ambientLight;
    private List<Primitive> primitives;
    private List<Mesh> meshes;

    public Scene(String name, Camera camera) {
        this.name = name;
        this.camera = camera;
        this.pointLights = new ArrayList<>();
        this.directedLights = new ArrayList<>();
        this.primitives = new ArrayList<>();
        this.meshes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Camera getCamera() {
        return camera;
    }

    public void addPointLight(PointLightSource pointLight) {
        pointLights.add(pointLight);
    }

    public void addDirectedLight(DirectedLightSource directedLight) {
        directedLights.add(directedLight);
    }

    public void setAmbientLight(AmbientLightSource ambientLight) {
        this.ambientLight = ambientLight;
    }

    public void addPrimitive(Primitive primitive) {
        primitives.add(primitive);
    }

    public void addMesh(Mesh mesh) {
        meshes.add(mesh);
    }

    public List<PointLightSource> getPointLights() {
        return pointLights;
    }

    public List<DirectedLightSource> getDirectedLights() {
        return directedLights;
    }

    public AmbientLightSource getAmbientLight() {
        return ambientLight;
    }

    public List<Primitive> getPrimitives() {
        return primitives;
    }

    public List<Mesh> getMeshes() {
        return meshes;
    }
}