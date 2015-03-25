package storm.heaven.monitor.entity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class TopologyInfo extends UIEntity{
	private static final String SPOUT_KEY="spouts";
	private Integer msgTimeout;
	private List<Spout> spouts;
	private Integer executorsTotal;
	private String uptime;
	private String encodedId;
	
	
	public Integer getMsgTimeout() {
		return msgTimeout;
	}


	public void setMsgTimeout(Integer msgTimeout) {
		this.msgTimeout = msgTimeout;
	}


	public List<Spout> getSpouts() {
		return spouts;
	}


	public void setSpouts(List<Spout> spouts) {
		this.spouts = spouts;
	}


	public Integer getExecutorsTotal() {
		return executorsTotal;
	}


	public void setExecutorsTotal(Integer executorsTotal) {
		this.executorsTotal = executorsTotal;
	}


	public String getUptime() {
		return uptime;
	}


	public void setUptime(String uptime) {
		this.uptime = uptime;
	}


	public String getEncodedId() {
		return encodedId;
	}


	public void setEncodedId(String encodedId) {
		this.encodedId = encodedId;
	}


	@Override
	public void parse(String result) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = mapper.readValue(result, new TypeReference<Object>() {
		});
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				// 若是final类型字段，不能改变其值
				boolean isFinal = Modifier.isFinal(fields[i].getModifiers());
				if (isFinal) {
					continue;
				}
				String fieldName=fields[i].getName();
				if(fieldName.equals("spouts")){
					@SuppressWarnings("unchecked")
					ArrayList<HashMap<String, String>> spoutMapList=(ArrayList<HashMap<String, String>>)map.get(SPOUT_KEY);
					spouts=new ArrayList<Spout>();
					for(HashMap<String, String> spoutMap:spoutMapList){
						Spout spout=new Spout();
						super.parse(spoutMap, spout);
						spouts.add(spout);
					}
					fields[i].set(this, spouts);
				}else{
					Object value = map.get(fields[i].getName());
					fields[i].set(this, value);
				}
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
