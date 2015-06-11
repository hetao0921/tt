package NVMP.Command.Business;

/**
 * 会商
 */
public class ConsultationGroup extends CommandTeam {

	public ConsultationGroup(String id, String name, String decription) {
		this.setId(id);
		this.setName(name);
		this.setDecription(decription);
		setCommanderLists(new java.util.ArrayList<Commander>());
	}

	public ConsultationGroup() {

	}

	protected void finalize() throws Throwable {

	}

	@Override
	public void dispose() {

	}

} // end ConsultationGroup