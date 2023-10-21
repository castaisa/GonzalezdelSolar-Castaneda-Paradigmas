package tp3;

public class DepthOfficer {

    public DepthLevels depth;

    public DepthOfficer(){
        depth = new SurfaceWaters();
    }

    public int getZ(){
        return depth.getZ(this);
    }

    public void goDown() {
        depth.goDown(this);
    }

    public void goUp(Submarine nemo) {
        depth.goUp(this);
    }

    public void liberateCapsule() {
        depth.liberateCapsule();
    }
}
