package tp3;

import tp3.DeepWaters;
import tp3.DepthLevels;
import tp3.DepthOfficer;
import tp3.SurfaceWaters;

public  class ShallowWaters extends DepthLevels {

    public  ShallowWaters (DepthLevels previousDeepness){
        previous = previousDeepness;
    }

    public int getZ(DepthOfficer depthOfficer) {
        return 1;
    }

    public DepthLevels goUp(DepthOfficer depthOfficer) {
        return depthOfficer.depth = new SurfaceWaters();
    }

    public DepthLevels goDown(DepthOfficer depthOfficer) {
        return depthOfficer.depth = new DeepWaters(this);
    }

    public void liberateCapsule() {
    }
}
