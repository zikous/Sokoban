package entity;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7a4676f4-ea89-40ed-b5f6-2561c23dec7a")
public class Entrepot {
    @objid ("4310f7f9-a42f-4231-9f22-c3fb5a313aaa")
    private List<Position> positions = new ArrayList<Position> ();

    @objid ("de67d322-7095-427b-a023-70a20df7efd9")
    private List<Zone> zones = new ArrayList<Zone> ();

}
