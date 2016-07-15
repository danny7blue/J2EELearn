package bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class RemoteCommandView {
	public void execute() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fuck", "what the hell"));
	}
}
