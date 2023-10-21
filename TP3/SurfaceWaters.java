package tp3;

public class SurfaceWaters extends DepthLevels {

    public int getZ(DepthOfficer depthOfficer) {
        return 0;
    }

    public DepthLevels goUp(DepthOfficer depthOfficer) {
        return this;
    }

    public DepthLevels goDown(DepthOfficer depthOfficer) {
        return depthOfficer.depth = new ShallowWaters(this);
    }

    public void liberateCapsule() {
    }
}
