package storm.heaven.monitor.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class SupervisorSummary extends UIEntity {
	private static final String SUPERVISOR_KEY="supervisors";
	private List<Supervisor> supervisors;

	public List<Supervisor> getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(List<Supervisor> supervisors) {
		this.supervisors = supervisors;
	}

	public void parse(String json) throws JsonParseException, JsonMappingException, IOException {
		// 解包接收到的字符串
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> map = mapper.readValue(json, new TypeReference<Object>() {
		});
		Object supervisorInfo = map.get(SUPERVISOR_KEY);
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> supervisorList = (ArrayList<HashMap<String, String>>) supervisorInfo;
		supervisors = new ArrayList<Supervisor>();
		for (HashMap<String, String> supervisorMap : supervisorList) {
			Supervisor supervisor = new Supervisor();
			super.parse(supervisorMap, supervisor);
			supervisors.add(supervisor);
		}
	}
}
