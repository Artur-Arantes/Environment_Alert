package br.com.fiap.environment.alert.service;

import br.com.fiap.environment.alert.domain.Resource;
import br.com.fiap.environment.alert.domain.ResourceIndex;
import br.com.fiap.environment.alert.dto.AddIndexInPut;
import br.com.fiap.environment.alert.dto.GetIndexOutPut;
import br.com.fiap.environment.alert.exception.ObjectNotFoundException;
import br.com.fiap.environment.alert.repository.ResourceIndexRepository;
import br.com.fiap.environment.alert.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IndexService {

    ResourceRepository resourceRepository;
    ResourceIndexRepository repository;


    public void add(final AddIndexInPut dto) {
        ResourceIndex index = new ResourceIndex();
        BeanUtils.copyProperties(dto, index);
        if (resourceRepository.findByName(dto.name()).isPresent()) {
            index.setResource(resourceRepository.findByName(dto.name()).orElseThrow(()
                    -> new ObjectNotFoundException("resource not found")));
        } else {
            index.setResource(Resource.builder()
                    .id(dto.id())
                    .name(dto.name())
                    .unitOfMeasure(dto.unityOfMeasure())
                    .build());
        }
        repository.save(index);

    }

    public void update(final AddIndexInPut dto) {
        ResourceIndex index = new ResourceIndex();
        BeanUtils.copyProperties(dto, index);
        if (!repository.existsById(index.getId())) {
            throw new ObjectNotFoundException("Resource do not exists");
        }
        repository.save(index);
    }

    public Page<GetIndexOutPut> getIndex(Pageable pagination) {
        Page<GetIndexOutPut> resultList = repository.findAll(pagination).map(ResourceIndex::toOutPutDto);
        if (resultList.isEmpty()) {
            throw new ObjectNotFoundException("Do not exists data");
        }
        return resultList;
    }
}
