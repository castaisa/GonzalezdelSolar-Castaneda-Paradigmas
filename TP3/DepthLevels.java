package tp3;

public abstract class DepthLevels {
    public DepthLevels previous;
    public abstract int getZ(DepthOfficer depthOfficer);
    public abstract DepthLevels goUp(DepthOfficer depthOfficer);
    public abstract DepthLevels goDown(DepthOfficer depthOfficer);
    public abstract void liberateCapsule();
}
