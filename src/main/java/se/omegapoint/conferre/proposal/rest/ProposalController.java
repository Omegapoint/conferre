package se.omegapoint.conferre.proposal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.proposal.domain.Proposal;
import se.omegapoint.conferre.proposal.service.ProposalService;

import java.util.List;

@RestController
@RequestMapping(path = "/proposal")
public class ProposalController {

    private final ProposalService proposalService;

    @Autowired
    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @RequestMapping
    public List<Proposal> listProposals() {
        return proposalService.listProposals();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Proposal createProposal(Proposal proposal) {
        return proposalService.createProposal(proposal);
    }

    @RequestMapping(path = "/{proposalId}", method = RequestMethod.PUT)
    public Proposal updateTitle(@PathVariable(value = "proposalId") Identity proposalId, @RequestParam(value = "title") String title) {
        return proposalService.updateTitle(proposalId, title);
    }
}
