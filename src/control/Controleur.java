package control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.Direction;
import entity.Entrepot;
import entity.Gardien;

@objid ("14f91e90-d328-4cde-b2ef-ec5fcc074d37")
public class Controleur {
    @objid ("eae08b9d-694a-4850-886b-2e97debff8ac")
    private Gardien gardien;

    @objid ("54da0ef2-d468-468a-9149-9b67e21c4ff3")
    private Entrepot entrepot;

    @objid ("b4eb871f-b7e0-471c-a89a-31c91670ec60")
    public void action(final Direction direction) {
    }

}
