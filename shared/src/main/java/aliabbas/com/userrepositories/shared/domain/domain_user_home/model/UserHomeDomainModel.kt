package aliabbas.com.userrepositories.shared.domain.domain_user_home.model

// only domain models hsould be exposed to UI layer eg. to viewModels zipped inside use-cases
// in short, A use-case will be injected into viewModel which will contain a domain model
// Do no use network layer or entity layer models inside use-cases or viewModels
// transform them with the help of mapper classes

data class UserHomeDomainModel(val any: Any)