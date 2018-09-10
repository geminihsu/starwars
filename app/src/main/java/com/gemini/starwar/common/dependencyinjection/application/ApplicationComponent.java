package com.gemini.starwar.common.dependencyinjection.application;

import com.gemini.starwar.common.dependencyinjection.presentation.PresentationComponent;
import com.gemini.starwar.common.dependencyinjection.presentation.PresentationModule;
import com.gemini.starwar.common.dependencyinjection.service.ServiceComponent;
import com.gemini.starwar.common.dependencyinjection.service.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {
    public PresentationComponent newPresentationComponent(PresentationModule presentationModule);
    public ServiceComponent newServiceComponent(ServiceModule serviceModule);
}
