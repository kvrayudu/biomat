package edu.cornell.cals.biomat.service;

import java.util.List;

public interface BioCompositionService {
	List<String> getNonExistingTagNames(List<String> tagNameList);
	List<String> getExistingTagNames(List<String> tagNameList);
}
