package math.app.service;

import math.app.dao.RootDao;
import math.app.model.Root;

public class RootServiceImpl implements RootService {
    private RootDao rootDao;

    public RootServiceImpl(RootDao rootDao) {
        this.rootDao = rootDao;
    }

    @Override
    public Root findByRoot(Double root) {
        return rootDao.findByRoot(root).orElse(new Root());
    }
}
