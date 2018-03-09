package se.omegapoint.conferre.proposal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.omegapoint.conferre.proposal.domain.Proposal;
import se.omegapoint.conferre.proposal.domain.ProposalRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/proposal")
public class ProposalController {

    private final ProposalRepository proposalRepository;

    @Autowired
    public ProposalController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @RequestMapping
    public List<Proposal> listProposals() {
        return proposalRepository.listProposals();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Proposal createProposal(Proposal proposal) {
        proposalRepository.createProposal(proposal);
        return proposal;
    }
}
