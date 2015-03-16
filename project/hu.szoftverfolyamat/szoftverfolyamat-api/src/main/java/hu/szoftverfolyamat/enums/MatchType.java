package hu.szoftverfolyamat.enums;

/**
 * Kereséseknél a mintaillesztés típusát adja meg.
 * 
 * <ul>
 * <li><b>EXACT: </b>csak pontos egyezés</li>
 * <li><b>SUBSTRING: </b>bármilyen része egyezik</li>
 * <li><b>PREFIX: </b>minden olyan bejegyzés találatnak számít, melynek prefixe a megadott string
 *
 * @author Peti
 *
 */
public enum MatchType {
	
	EXACT, SUBSTRING, PREFIX

}
