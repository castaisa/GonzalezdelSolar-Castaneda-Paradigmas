package tp3;

public class DeepWaters extends DepthLevels {
    public  DeepWaters(DepthLevels previousDeepness){
        previous = previousDeepness;
    }

    public int getZ(DepthOfficer depthOfficer) {
        return 1 + previous.getZ(depthOfficer);
    }

    public DepthLevels goUp(DepthOfficer depthOfficer) {
        return depthOfficer.depth = previous;
    }

    public DepthLevels goDown(DepthOfficer depthOfficer) {
        return depthOfficer.depth = new DeepWaters(this);
    }

    public void liberateCapsule() {
        throw new RuntimeException(Submarine.ElSubmarinoHaSidoDestruido);
    }
}
