package posweb.atividade01.enums;

public enum SituacaoDisciplinaEnum {
	EM_CURSO(0, "Em Curso"),
	APROVADO(1, "Aprovado"),
	REPROVADO(2, "Reprovado"),
	TRANCADO(3, "Trancado");
	
	private final Integer key;
	private final String value;
	
	SituacaoDisciplinaEnum(Integer key, String value){
		this.key = key;
		this.value = value;
	}
	
	public Integer getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
}
